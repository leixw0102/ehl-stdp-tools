package com.ehl.stdp.collecter.hdfs;

import com.ehl.stdp.collecter.AbstractConnection;
import com.ehl.stdp.collecter.CallBack;
import org.apache.hadoop.conf.Configuration;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public abstract class AbstractHdfsConnection extends AbstractConnection<String> {
    Configuration conf = new Configuration();
    public AbstractHdfsConnection(){
        this("root");
    }

    public AbstractHdfsConnection(String user){
        System.setProperty("HADOOP_USER_NAME",user);
    }

    @Override
    public String execute(CallBack callBack) throws Exception {
        throw new Exception("nothing");
    }
}
