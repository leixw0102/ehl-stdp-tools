package com.ehl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by 雷晓武 on 2017/7/10.
 */
public interface Sql4Plugin {

    String sql_select_1="select * from {0}";
    String sql_select_2=sql_select_1+" limit 5";
    String sql_select_3=sql_select_2+",5";
    String sql_select_4=sql_select_1+" where timestamp>=1476770984000";
    String sql_select_5=sql_select_1+" where timestamp between  1476770984000 and 1476770994000";
    String  sql_select_6 = sql_select_1+" where car_plate_number =term('京a35076') and bay_id ='10085'";
    String sql_select_7 =sql_select_1+ " where car_plate_number =term('京a35076') or timestamp>=1476770984000";
    String sql_select_8=sql_select_1 +" where car_plate_number LIKE \"京a%\" limit 5";//" where car_plate_number_index like '%a3507%'";
    String sql_select_9=sql_select_1+" where timestamp in(1476770984000,1476770994000)";
    //order
    String sql_select_10=sql_select_1+ " order by timestamp asc limit 10";
    String sql_select_11=sql_select_1+" order by timestamp desc limit 10";
    String sql_count_1="select count(*) from {0} ";
    String sql_count_2 = sql_count_1+" where car_plate_number =term('京a35076')";
    String sql_group_1="select car_plate_number, count(*) from {0} group by car_plate_number limit 10";
    //EXTENDED_STATS(speed).count	EXTENDED_STATS(speed).sum	EXTENDED_STATS(speed).avg	EXTENDED_STATS(speed).min	EXTENDED_STATS(speed).max	EXTENDED_STATS(speed).sumOfSquares	EXTENDED_STATS(speed).variance	EXTENDED_STATS(speed).stdDeviation
    //561794	3.3715912E7	60.014724258358044	0.0	199.0	2.4805885E9	813.70989757978	28.525600740033152
    String sql_group_2="select EXTENDED_STATS(speed) from {0}  where timestamp>=1476770984000";

    String sql_group_3 = "select STATS(speed) from {0} where timestamp>=1476770984000 ";
    // PERCENTILES(speed).1.0	PERCENTILES(speed).5.0	PERCENTILES(speed).25.0	PERCENTILES(speed).50.0	PERCENTILES(speed).75.0	PERCENTILES(speed).95.0	PERCENTILES(speed).99.0
    //11.0	15.0	35.939634398703454	60.0	84.99788843935374	100.0	120.0
    String sql_group_4="select PERCENTILES(speed) from {0} where timestamp>=1476770984000";
    String sql_group_5="select PERCENTILES(speed，25,75) from {0} where timestamp>=1476770984000";

    //date_histogram,用于图表展示
    String sql_group_6="select count(*) from {0} group by date_histogram(field='timestamp',\"interval\"='1M',\"format\"='yyyy-MM')";
    //不支持
    String sql_group_7="select count(*) from {0} group by date_range(field='timestamp',\"interval\"='1M','format'='yyyy-MM','2016-08','now')";
    //不支持
    String sql_count_8="select topHits('size'=3,'speed'=\"desc\"),count(*) from {0} group by timestamp";
    //multiple index/type
    List<String> sqls = Lists.newArrayList(sql_select_1,sql_select_2,sql_select_3,sql_select_4,
            sql_select_5,sql_select_6,sql_select_7,sql_select_8,sql_select_9,sql_select_10,sql_select_11,
            sql_count_1,sql_count_2,sql_group_1,sql_group_2,sql_group_3,sql_group_4,sql_group_5,
            sql_group_6);
    String sql_multiple_1="select * from {0} limit 10";


    //TODO alias not support
    //other phrase

}
