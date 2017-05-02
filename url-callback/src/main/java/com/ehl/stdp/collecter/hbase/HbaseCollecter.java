package com.ehl.stdp.collecter.hbase;

import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.EhlConnection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public class HbaseCollecter extends AbstraceHbaseConnection implements CapacityCollecter  {
    Configuration conf = HBaseConfiguration.create();
    Connection connection=null;
    Admin admin=null;

    public HbaseCollecter(){
        //后期优化
//        init();
    }

    @Override
    public String getTotalCapacity() throws Exception {
//        HTableDescriptor h = admin.getTableDescriptor(TableName.valueOf(""));

        return null;
    }
    //参考hdfs
    @Override
    public String getCapacityByName(String file) throws Exception {
        return null;
    }

    @Override
    public String percentageByName(String name) {
        return null;
    }

}
