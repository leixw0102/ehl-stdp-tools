package com.ehl.stdp.collecter.es;


/**
 * Created by 雷晓武 on 2017/3/27.
 */
public class EsCheck extends AbstractEsConnection implements EsUrls {
    @Override
    public boolean isConnectioned() {
        try {
            String result = httpGetEs2Json(isConnectedUrl,conf.get("es.ip"),conf.get("es.port"));
            System.out.println(result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
