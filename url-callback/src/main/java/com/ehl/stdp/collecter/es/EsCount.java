package com.ehl.stdp.collecter.es;

import com.ehl.stdp.collecter.SizeCollecter;
import com.jayway.jsonpath.JsonPath;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public class EsCount extends AbstractEsConnection implements EsUrls,SizeCollecter {
    @Override
    public long getSize(String table) throws Exception {
        String body = httpGetEs2Json(indexStatusUrlFormat,conf.get("es.ip"),conf.get("es.port"),table);
        if(null == body) throw new Exception("");
        return Long.parseLong(JsonPath.read(body,count2long).toString());
    }
}
