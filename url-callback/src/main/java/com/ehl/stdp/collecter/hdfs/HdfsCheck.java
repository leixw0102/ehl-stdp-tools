package com.ehl.stdp.collecter.hdfs;

import com.ehl.stdp.collecter.CheckConnection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/3/27.
 */
public class HdfsCheck extends AbstractHdfsConnection implements CheckConnection {

    @Override
    public boolean isConnectioned() throws Exception {
        try {
            FileSystem.get(conf).close();
            return true;
        } catch (IOException e) {
           throw new Exception("hdfs connecting error:"+e.getMessage());
        }
    }

    @Override
    public String getSummary() {
        return null;
    }
}
