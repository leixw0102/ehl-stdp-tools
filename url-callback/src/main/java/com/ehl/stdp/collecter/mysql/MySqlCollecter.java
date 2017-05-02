package com.ehl.stdp.collecter.mysql;

import com.ehl.stdp.collecter.AbstractDBConnection;
import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;

/**
 *
 * SELECT CONCAT(TRUNCATE(SUM(data_length)/1024/1024,2),'MB') AS data_size,
 CONCAT(TRUNCATE(SUM(max_data_length)/1024/1024,2),'MB') AS max_data_size,
 CONCAT(TRUNCATE(SUM(data_free)/1024/1024,2),'MB') AS data_free,
 CONCAT(TRUNCATE(SUM(index_length)/1024/1024,2),'MB') AS index_size
 FROM information_schema.tables WHERE TABLE_SCHEMA = 'db';



 MySQL中有一个名为 information_schema 的数据库，在该库中有一个 TABLES 表，这个表主要字段分别是：

 TABLE_SCHEMA : 数据库名
 TABLE_NAME：表名
 ENGINE：所使用的存储引擎
 TABLES_ROWS：记录数
 DATA_LENGTH：数据大小
 INDEX_LENGTH：索引大小

 example:
 select  TABLE_NAME,
 (DATA_LENGTH/1024/1024) as DataM ,
 (INDEX_LENGTH/1024/1024) as IndexM,
 ((DATA_LENGTH+INDEX_LENGTH)/1024/1024) as AllM,
 TABLE_ROWS   FROM information_schema.tables WHERE TABLE_SCHEMA = 'ambari';
 *
 * Created by 雷晓武 on 2017/3/24.
 */
public class MySqlCollecter extends AbstractDBConnection implements CapacityCollecter{
    public MySqlCollecter(){
        this("mysql.conf");
    }

    public MySqlCollecter(String file){
        super(file);
    }

    @Override
    public String getTotalCapacity() throws Exception {
        return null;
    }
    final String data_length_sql = "select TABLE_NAME,DATA_LENGTH+INDEX_LENGTH as allByte,TABLE_ROWS " +
            "from information_schema.tables ";
    @Override
    public String getCapacityByName(String file) throws Exception {
        final  String sql = data_length_sql+" WHERE TABLE_NAME= '"+file+"'";
        System.out.println(sql);
        return execute(new CallBack<Connection, String>() {
            @Override
            public String execute(Connection connection) throws Exception {
                PreparedStatement pst=null;
                ResultSet rs = null;
                try{
                    pst = connection.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        return rs.getString(1)+"\t"+rs.getLong(2)+"\t"+rs.getLong(3);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(null != rs){
                        rs.close();
                    }
                    if(null != pst){
                        pst.close();
                    }
                }
                return "NAN";
            }
        });
    }

    @Override
    public String percentageByName(String name) {
        return null;
    }

    public static void main(String[]args) throws Exception {
        new MySqlCollecter().getCapacityByName("DS_UDF_6");
    }
}
