package com.ehl.es.sq;


import com.ehl.Sql4Calcite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Properties;
/**
 * Created by 雷晓武 on 2017/6/30.
 */
public class EsSql4CalciteTest implements Sql4Calcite {
    /**
     * error count 支持错误
     * where _MAP['property']
     * select *
     * desc timestamp不支持
     * group by 不支持
     */
//    static List<String> sqls = null;
    private static Logger logger = LoggerFactory.getLogger(EsSql4CalciteTest.class);
//    static {
//        sqls = Lists.newArrayList(sql_1,sql_3,sql_4,sql_5,sql_6,sql_7,sql_8,sql_9,sql_10,sql_11,sql_12);
//    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.put("model",resourcePath("es.json"));
        try(Connection connection = DriverManager.getConnection("jdbc:calcite:",info)){
//
            ResultSet rs= connection.getMetaData().getTables(null,null,null,null);
            while (rs.next()){
                    logger.info( "Catalog : " + rs.getString(1) + ",Database : " + rs.getString(2) + ",Table : " + rs .getString(3));
            }
            System.out.println(".....");//select * from "PoliceOrientation" offset 2 rows fetch next 3 rows only
//            for(String sql : sqls) {
                ResultSet rs1 =null;
                Statement statement = connection.createStatement();
                rs1 = statement.executeQuery(sql_13);
                while (rs1.next()){
                    logger.info(rs1.getLong(1) +"\t" +rs1.getString(2)+"\t"+rs1.getString(3));
                }
                logger.info("--------------------------------------------------------------------");
                statement.close();

//            }
        };
//        Executors.newCachedThreadPool();

    }



    private static String resourcePath(String path) {
        final URL url =EsSql4CalciteTest.class.getResource("/" + path);
        // URL converts a space to %20, undo that.
        try {
            String s = URLDecoder.decode(url.toString(), "UTF-8");
            if (s.startsWith("file:")) {
                s = s.substring("file:".length());
            }
            return s;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
