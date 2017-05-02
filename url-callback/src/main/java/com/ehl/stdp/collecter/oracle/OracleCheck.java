package com.ehl.stdp.collecter.oracle;

import com.ehl.stdp.collecter.AbstractDBConnection;
import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.CheckConnection;

import java.sql.Connection;

/**
 * Created by 雷晓武 on 2017/3/27.
 */
public class OracleCheck extends AbstractDBConnection implements CheckConnection {

    public OracleCheck(){
        this("oracle.conf");
    }

    public OracleCheck(String file) {
        super(file);
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
    public String getSummary() {
        return null;
    }
}
