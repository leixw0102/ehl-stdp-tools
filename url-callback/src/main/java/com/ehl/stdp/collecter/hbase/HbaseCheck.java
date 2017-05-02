package com.ehl.stdp.collecter.hbase;

import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.CheckConnection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public class HbaseCheck extends AbstraceHbaseConnection{

    public static void main(String[]args) throws Exception {
        CheckConnection check = new HbaseCheck();
        System.out.println(check.isConnectioned());
        System.out.println(check.isConnectioned());
    }
}
