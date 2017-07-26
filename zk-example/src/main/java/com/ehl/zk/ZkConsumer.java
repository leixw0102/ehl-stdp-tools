package com.ehl.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by 雷晓武 on 2017/6/5.
 */
public class ZkConsumer {

    private static CuratorFramework getClient(String connectionString){

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,4);

        return CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
    }

    public static void watcher(CuratorFramework client,String path) throws Exception {
        byte[] value = client.getData().watched().forPath(path);
        System.out.println(new String (value));
    }

    public static void main(String[]args) throws Exception {
        CuratorFramework client = getClient("10.2.111.108:2181");
        client.start();
        watcher(client,"/ehl/query");

    }
}
