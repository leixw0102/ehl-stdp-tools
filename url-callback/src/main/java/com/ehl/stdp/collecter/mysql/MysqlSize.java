package com.ehl.stdp.collecter.mysql;

import com.ehl.stdp.collecter.AbstractDBConnection;
import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.SizeCollecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;

/**
 * Created by 雷晓武 on 2017/4/13.
 */
public class MysqlSize extends AbstractDBConnection implements SizeCollecter {
    public MysqlSize() {
        this("mysql.conf");
    }

    public MysqlSize(String file){
        super(file);
    }
    String sql = "select count(*) from {0}";
    @Override
    public long getSize(String table) throws Exception {
        final String _sql = MessageFormat.format(sql,table);
        System.out.println(_sql);

        return execute(new CallBack<Connection, Long>() {
            @Override
            public Long execute(Connection connection) throws Exception {
                PreparedStatement pst=null;
                ResultSet rs = null;
                try{
                    pst = connection.prepareStatement(_sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        return rs.getLong(1);
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
                return 0L;
            }
        });
    }
}
