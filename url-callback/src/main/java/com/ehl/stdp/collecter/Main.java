package com.ehl.stdp.collecter;

import com.ehl.stdp.collecter.es.EsCollecter;
import com.ehl.stdp.collecter.es.EsMain;
import com.ehl.stdp.collecter.hbase.HbaseMain;
import com.ehl.stdp.collecter.hdfs.HdfsCollecter;
import com.ehl.stdp.collecter.hdfs.HdfsMain;
import com.ehl.stdp.collecter.mysql.MySqlCollecter;
import com.ehl.stdp.collecter.mysql.MysqlMain;
import com.ehl.stdp.collecter.oracle.OracleCollecter;
import com.ehl.stdp.collecter.oracle.OracleMain;
import com.google.common.collect.Maps;
import sun.misc.Unsafe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by 雷晓武 on 2017/3/23.
 */
public class Main {

    private ConcurrentMap<String,Class<?>> mains = Maps.newConcurrentMap();
    public Main(){
        init();
    }

    public void init() {
        mains.put("hdfs", HdfsMain.class);
        mains.put("mysql", MysqlMain.class);
        mains.put("oracle", OracleMain.class);
        mains.put("es", EsMain.class);
        mains.put("hbase", HbaseMain.class);
    }

    public void execute() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Set<String> sets = mains.keySet();
        for(String k:sets){
            Class v = mains.get(k);
            System.out.println(v.getName());
            Method method = v.getMethod("main",String[].class);
            method.invoke(null,(Object)new String[]{"1","2"});
        }
}

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        new Main().execute();
    }
}
