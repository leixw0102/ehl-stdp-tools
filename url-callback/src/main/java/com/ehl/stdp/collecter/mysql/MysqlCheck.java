package com.ehl.stdp.collecter.mysql;

import com.ehl.stdp.collecter.AbstractDBConnection;
import com.ehl.stdp.collecter.CheckConnection;
import com.ehl.stdp.collecter.utils.JdbcUtils;

import java.sql.Connection;

/**
 * Created by 雷晓武 on 2017/3/27.
 */
public class MysqlCheck extends AbstractDBConnection implements CheckConnection {

    public MysqlCheck(){
        this("mysql.conf");
    }

    public MysqlCheck(String file){
        super(file);
    }

    @Override
    public boolean isConnectioned() throws Exception {
        return super.isConnectioned();
    }

    @Override
    public String getSummary() {
        return super.toString();
    }
}
