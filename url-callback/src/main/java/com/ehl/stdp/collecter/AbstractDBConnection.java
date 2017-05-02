package com.ehl.stdp.collecter;

import com.ehl.offline.common.EhlConfiguration;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 雷晓武 on 2017/4/13.
 */
public abstract class AbstractDBConnection extends AbstractConnection<Connection> implements JdbcConstant{
    EhlConfiguration conf=null;

    BoneCP cp = null;
    Connection connection = null;
    private boolean check(){
        return true;
    }

    /**
     * shut down hook
     */
    class ShutDownThread extends Thread{
        @Override
        public void run() {
            close();
           if(null != cp){
               cp.shutdown();
           }
        }
    }


    String _url ,_username,_password;

    public AbstractDBConnection(String file){
        conf = new EhlConfiguration().addResource(file);
        if(check()){
            try {
                Class.forName(conf.get(driver));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            _url = conf.get(url);
            _username = conf.get(user);
            _password = conf.get(password);
           _init();

            Runtime.getRuntime().addShutdownHook(new ShutDownThread());

        }

    }

    private void _init(){
        try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(_url); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setUsername(_username);
            config.setPassword(_password);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            cp = new BoneCP(config); // setup the connection pool
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void close() {
        if(null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isConnectioned() throws Exception {

        return execute(new CallBack<Connection, Boolean>() {
            @Override
            public Boolean execute(Connection connection) throws Exception {
                return !connection.isClosed();
            }
        });
    }

    @Override
    public void init() throws IOException {
        try {
            if(null == cp){
                _init();
            }
            connection = cp.getConnection(); // fetch a connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public synchronized  <O> O execute(CallBack<Connection, O> callBack) throws Exception {
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

    @Override
    public String toString() {
        return _url+"\t"+_username+"\t"+_password;
    }
}
