package com.ehl.stdp.collecter.es;

/**
 * Created by 雷晓武 on 2017/3/24.
 */
public interface EsUrls {

    String indexStatusUrlFormat="http://{0}:{1}/{2}/_stats?pretty";

    String size2byte="$._all.primaries.store.size_in_bytes";

    String count2long="$._all.primaries.docs.count";

    String isConnectedUrl="http://{0}:{1}";
}
