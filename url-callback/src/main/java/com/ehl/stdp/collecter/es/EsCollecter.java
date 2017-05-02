package com.ehl.stdp.collecter.es;

import com.ehl.offline.common.EhlConfiguration;
import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.SizeCollecter;
import com.ehl.stdp.collecter.http.EhlHttpClient;
import com.jayway.jsonpath.JsonPath;

import java.text.MessageFormat;

/**
 * Created by ehl on 2016/6/5.
 */
public class EsCollecter extends AbstractEsConnection implements EsUrls,CapacityCollecter {
//
//    public void execute(String index) throws Exception {
//        String body = httpGetEs2Json(indexStatusUrlFormat,conf.get("es.ip"),conf.get("es.port"),index);
//        if(null == body) throw new Exception("");
//        totalByte=JsonPath.read(body,size2byte).toString();
//        count = JsonPath.read(body,count2long);
//    }

    public static void main(String[]args) throws Exception {
        EsCollecter es = new EsCollecter();
        String a=es.getCapacityByName("ehlindex");
        System.out.println(a);

//        System.out.println(jsonObject.get("_all"));
//        String result = httpGetExecute("http://10.150.27.222:8888/es");
//        System.out.println(result);
    }
    @Override
    public String getTotalCapacity() throws Exception {
        return null;
    }

    @Override
    public String getCapacityByName(String file) throws Exception {
        String body = httpGetEs2Json(indexStatusUrlFormat,conf.get("es.ip"),conf.get("es.port"),file);
        if(null == body) throw new Exception("");
        return JsonPath.read(body,size2byte).toString();
        //return JsonPath.read(body,"$._all.primaries.docs.count").toString();
    }

    @Override
    public String percentageByName(String name) {
        return null;
    }

}
