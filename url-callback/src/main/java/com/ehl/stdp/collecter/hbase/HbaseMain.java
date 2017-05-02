package com.ehl.stdp.collecter.hbase;

import com.ehl.stdp.collecter.AbstractTest;
import com.ehl.stdp.collecter.CheckConnection;
import com.ehl.stdp.collecter.SizeCollecter;

/**
 * Created by 雷晓武 on 2017/4/12.
 */
public class HbaseMain extends AbstractTest {
    @Override
    public void testCheck() throws Exception {
        CheckConnection connection = new HbaseCheck();
        boolean isConnected = connection.isConnectioned();
        System.out.println(isConnected);
        assert isConnected :"hbase connection closed";
    }

    @Override
    public void testSize() throws Exception {
        SizeCollecter sizeCollecter = new HbaseCount();
        long  size = sizeCollecter.getSize("mobile");
        System.out.println(size);
        assert size>0 :"size =0";
    }

    @Override
    public void testCapacity() throws Exception {
        //TODO
        System.out.println("hdfs implements");
    }

    public static void main(String[]args) throws Exception {
        HbaseMain main = new HbaseMain();
        main.test();
    }

}
