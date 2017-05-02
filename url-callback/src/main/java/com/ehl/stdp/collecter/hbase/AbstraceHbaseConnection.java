package com.ehl.stdp.collecter.hbase;

import com.ehl.stdp.collecter.AbstractConnection;
import com.ehl.stdp.collecter.CallBack;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public abstract class AbstraceHbaseConnection extends AbstractConnection<Connection> {
    Connection connection = null;
    Configuration conf = null;

    public AbstraceHbaseConnection(){
        conf = HBaseConfiguration.create();
    }

    @Override
    public void init() throws IOException {
        connection = ConnectionFactory.createConnection(conf);
    }

    @Override
    public boolean isConnectioned() throws Exception {
        return !execute(new CallBack<Connection, Boolean>() {
            @Override
            public Boolean execute(Connection connection) throws Exception {
                return connection.isClosed();
            }
        });
    }

    @Override
    public void close() {
        if(null != connection){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public synchronized  <O> O execute(CallBack<Connection, O> callBack) {
        try{
            init();
            return callBack.execute(connection);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            close();
        }
        return null;
    }
}
