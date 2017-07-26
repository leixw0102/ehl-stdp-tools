package com.ehl.es4sql;

import com.alibaba.druid.pool.*;
import com.alibaba.druid.util.jdbc.ResultSetMetaDataBase;
import com.ehl.Sql4Plugin;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by 雷晓武 on 2017/7/11.
 */
public class Es4Jdbc implements Sql4Plugin {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String,String> getInitMaps(){
        Map<String,String> maps = Maps.newHashMap();
        maps.put("url","jdbc:elasticsearch://10.150.27.213:9300/ehlindex");
        return maps;
    }

    interface CallBack{
       void printlnAndClose(ResultSet rs);
    }



    public void test(){
        for(String sql : sqls){
            final String tempSql = MessageFormat.format(sql,"ehlindex/pass_car");
            testJdbc(tempSql, new CallBack() {
                @Override
                public void printlnAndClose(ResultSet rs)  {
                    try {
                        ResultSetMetaData rsmd =  rs.getMetaData();
                        StringBuffer category = new StringBuffer();
                        for(int i=0;i<rsmd.getColumnCount();i++){
                            category.append(rsmd.getCatalogName(i)+"\t");
                        }

                        logger.info(tempSql);
                        logger.info("category = {}",category.toString());

                        while (rs.next()){
                            StringBuffer values = new StringBuffer();
                            for(int i=0 ;i<rsmd.getColumnCount();i++){
                                values.append(rs.getObject(i)+"\t");
                            }
                            logger.info("value={}",values.toString());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void testJdbc(String sql,CallBack callBack){
        DataSource dataSource = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement espst=null;
        try {
            dataSource =  ElasticSearchDruidDataSourceFactory.createDataSource(getInitMaps());
            connection = dataSource.getConnection();
            System.out.println(sql);
            espst =  connection.prepareStatement(sql);
            rs=  espst  .executeQuery();
            callBack.printlnAndClose(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {
        Es4Jdbc es4Jdbc = new Es4Jdbc();
        es4Jdbc.test();
    }

}
