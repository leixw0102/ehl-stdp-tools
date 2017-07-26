package com.ehl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 雷晓武 on 2017/7/10.
 */
public interface Sql4Calcite {
      String sql_1="select * from \"pass_car\" ";
    //    private static String sql_2="select count(*) from \"pass_car\"";
      String sql_3="select  *  from \"pass_car\" where \"timestamp\" =1476838699000";
      String sql_4="select * from \"pass_car\" order by \"tp3\" DESC ";
      String sql_5=sql_4+" offset 2 rows fetch next 3 rows only";
      String sql_6 = sql_3+" offset 2 rows fetch next 3 rows only";
      String sql_7 =sql_3 +" fetch next 3 rows only";
      String sql_8 =sql_4 + "fetch next 3 rows only";
      String sql_9=sql_1+" fetch next 3 rows only";
      String sql_10=sql_1 +" offset 2 rows fetch next 3 rows only";
      String sql_11 = sql_1+" where \"timestamp\">1476838808000 and \"timestamp\"<=1476838699000";
    String sql_12 = sql_1+" where \"timestamp\" in (1476838808000,1476838776000)";
    String sql_13="select \"car_plate_number\" from \"pass_car\" group by \"car_plate_number\"";


    List<String> sqls = Lists.newArrayList(sql_1,sql_3,sql_4,sql_5,sql_6,sql_7,sql_8,sql_9,sql_10,sql_11,sql_12);
}
