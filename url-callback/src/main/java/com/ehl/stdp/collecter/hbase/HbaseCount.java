package com.ehl.stdp.collecter.hbase;

import com.ehl.stdp.collecter.CallBack;
import com.ehl.stdp.collecter.SizeCollecter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public class HbaseCount extends AbstraceHbaseConnection implements SizeCollecter {

    /**
     * 或者使用sql
     * @param table
     * @return
     */
    @Override
    public long getSize(final String table) {

        return execute(new CallBack<Connection, Long>() {
            @Override
            public Long execute(Connection connection) throws Exception {
                initAgg(table,connection);
                AggregationClient ac = new AggregationClient(conf);
                Scan scan = new Scan();
                long rowCount = 0;
                try {
                    rowCount = ac.rowCount(TableName.valueOf(table), new LongColumnInterpreter(), scan);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return rowCount;
            }
        });
    }

    final String coprocessClassName = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";
    private void initAgg(String table,Connection connection){
        Admin admin = null;
        HTableDescriptor htd = null;
        try {
            admin = connection.getAdmin();
            htd = admin.getTableDescriptor(TableName.valueOf(table));
            List<String> aggs=htd.getCoprocessors();

            if(aggs.contains(coprocessClassName)){
                return;
            }else {
                admin.disableTable(TableName.valueOf(table));
                htd.addCoprocessor(coprocessClassName);
                admin.modifyTable(TableName.valueOf(table), htd);
                admin.enableTable(TableName.valueOf(table));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != admin){
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String []args){
        System.out.println(new Date().toLocaleString());
        Long a=new HbaseCount().getSize("mobile");
        System.out.println(a+"\t"+new Date().toLocaleString());
    }
}
