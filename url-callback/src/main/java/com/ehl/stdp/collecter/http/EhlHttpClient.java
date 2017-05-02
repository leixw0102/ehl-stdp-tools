package com.ehl.stdp.collecter.http;

import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by ehl on 2016/6/5.
 */
public  class EhlHttpClient {
    private static String defaultUTF="utf-8";
    private static Charset UTF_8=Charset.forName(defaultUTF);
    private static int socket_timeout=120000;
    private static int connection_timeout=120000;
    private static final int DEFAULT_MAX_NUM_PER_ROUTE = 20;
    private static final int DEFAULT_MAX_TOTAL_NUM = 50;

    public static CloseableHttpClient getHttpClient(){

        HttpClientBuilder builder = HttpClients.custom();


        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(DEFAULT_MAX_NUM_PER_ROUTE);
        manager.setMaxTotal(DEFAULT_MAX_TOTAL_NUM);
        builder.setConnectionManager(manager);
        builder.setMaxConnPerRoute(DEFAULT_MAX_NUM_PER_ROUTE);
        builder.setMaxConnTotal(DEFAULT_MAX_TOTAL_NUM);
        return builder.build();
    }

    private static RequestConfig getRequestConfig(){
        return RequestConfig.custom()
                .setSocketTimeout(socket_timeout)
                .setConnectTimeout(connection_timeout)
                .build();
    }




    public static String httpGetContent(String url) throws Exception{

        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse response=null;
        HttpGet get =null;
        try{
            get =  new HttpGet(url);
            get.setConfig(getRequestConfig());
            response = client.execute(get);
            checkHeader(response.getStatusLine());
            return toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    protected static void checkHeader(StatusLine line) throws Exception {
        if(line.getStatusCode()!=HttpStatus.SC_OK){
            throw new Exception(line.getReasonPhrase()+"\t"+line.getStatusCode());
        }
    }


    private static String buildGetUrl(String url,Map<String,Object> params){
        StringBuffer uriStr = new StringBuffer(url);
        if (params != null) {
            List<NameValuePair> ps = Lists.newArrayList();
            for (String key : params.keySet()) {
                Object value=params.get(key);
                if(null == value){
                    continue;
                }
                ps.add(new BasicNameValuePair(key, value.toString()));
            }
            uriStr.append("?");
            uriStr.append(URLEncodedUtils.format(ps, UTF_8));
        }
        return uriStr.toString();
    }



    protected static String toString(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity,UTF_8);
    }
}
