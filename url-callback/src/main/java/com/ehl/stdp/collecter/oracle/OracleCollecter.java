package com.ehl.stdp.collecter.oracle;

import com.ehl.stdp.collecter.AbstractDBConnection;
import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * SELECT TABLESPACE_NAME,SEGMENT_NAME,sum(bytes)
 FROM DBA_EXTENTS WHERE
 SEGMENT_TYPE LIKE 'TABLE%' and tablespace_name='USERS'
 GROUP BY TABLESPACE_NAME,SEGMENT_NAME;
 * Created by 雷晓武 on 2017/3/27.
 */
public class OracleCollecter extends AbstractDBConnection implements CapacityCollecter {

    private String capacitySql="SELECT segment_name AS TABLENAME,BYTES B FROM user_segments";
    private JdbcUtils jdbcUtils=null;
    public OracleCollecter(){
        this("oracle.conf");
    }

    public OracleCollecter(String file){
        super(file);
    }

    @Override
    public String getTotalCapacity() throws Exception {
        return null;
    }

    @Override
    public String getCapacityByName(String file) throws Exception {
        final String sql = capacitySql+" WHERE segment_name= upper('"+file+"')";
        System.out.println(sql);
        return execute(new CallBack<Connection, String>() {
            @Override
            public String execute(Connection connection) throws Exception {
                PreparedStatement pst = null;ResultSet rs = null;
                try {

                    pst = connection.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        return rs.getString(1)+"\t"+rs.getLong(2);
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != rs){
                        rs.close();
                    }
                    if(null != pst){
                        pst.close();
                    }
                }
                return null;
            }
        });

    }

    @Override
    public String percentageByName(String name) {
        return null;
    }

    public static void main(String[]args) throws Exception {
       String value = new OracleCollecter().getCapacityByName("test1");
        System.out.println(value);
    }
}
