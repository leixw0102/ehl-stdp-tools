package com.ehl.stdp.collecter.es;

import com.ehl.offline.common.EhlConfiguration;
import com.ehl.stdp.collecter.AbstractConnection;
import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.http.EhlHttpClient;

import java.text.MessageFormat;


/**
 * Created by 雷晓武 on 2017/4/1.
 */
public abstract class AbstractEsConnection extends AbstractConnection<String> {
    EhlConfiguration conf = null;
    public AbstractEsConnection(String file){
        conf = new EhlConfiguration().addResource("es.conf");
    }

    public AbstractEsConnection(){
        this("es.conf");
    }
    public  synchronized String httpGetEs2Json(String url,String ...args) throws Exception {
        String esUrl = MessageFormat.format(url ,args);
        System.out.println(esUrl);
        try {
            return EhlHttpClient.httpGetContent(esUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
//    public void execute(String url,String index) throws Exception {
//        String body = httpGetEs2Json(indexStatusUrlFormat,conf.get("es.ip"),conf.get("es.port"),index);
//        if(null == body) throw new Exception("");
//        totalByte= JsonPath.read(body,size2byte).toString();
//        count = JsonPath.read(body,count2long);
//    }


    @Override
    public String execute(CallBack callBack) throws Exception {
        throw new Exception("");
    }
}
