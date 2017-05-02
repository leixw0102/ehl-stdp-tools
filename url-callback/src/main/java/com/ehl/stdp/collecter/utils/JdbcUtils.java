package com.ehl.stdp.collecter.utils;

import com.ehl.offline.common.EhlConfiguration;
import com.google.common.base.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 雷晓武 on 2017/3/24.
 */
public class JdbcUtils {
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public JdbcUtils(String file) throws Exception {
        this(file,"jdbc.driverClassName","jdbc.url","jdbc.password","jdbc.username");
    }

    public JdbcUtils(String file,String urlName,String driverName,String userName,String pwdName) throws Exception {
        EhlConfiguration conf = new EhlConfiguration().addResource(file);
        conf.foreach();
        driver = conf.get(driverName);
        url = conf.get(urlName);
        user = conf.get(userName);
        pwd = conf.get(pwdName);

        if(Strings.isNullOrEmpty(driver)){
            throw new NullPointerException("driver null");
        }
        if(Strings.isNullOrEmpty(url)){
            throw new NullPointerException("url null");
        }
        System.out.println(toString());
        try {
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public String toString() {
        return "JdbcUtils{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    /**
     * 释放数据库连接
     */
    public void releaseConn(Connection connection){
        if(connection != null){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[]args) throws Exception {
        //new MySqlCollecter().getCapacityByName("DS_UDF_6");
//        Class.forName("com.mysql.jdbc.Driver");
        JdbcUtils jdbcUtils = new JdbcUtils("mysql.conf","jdbc.driverClassName","jdbc.url","jdbc.password","jdbc.username");
    }


}
