package com.ehl.zk;

import com.sun.net.httpserver.Authenticator;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by 雷晓武 on 2017/6/2.
 */
public class ZKProducer {



    private static CuratorFramework getClient(String connectionString){

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,4);

        return CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
    }


    public static void send2zk(CuratorFramework client ,String path,String value) throws Exception {
        client.setData().forPath(path,value.getBytes());
    }

    public static void main(String []args) throws Exception {
        CuratorFramework client = getClient("10.2.111.108:2181");
        client.start();
        send2zk(client,"/zk-huey/cnode","dsfsdf");
    }

}
