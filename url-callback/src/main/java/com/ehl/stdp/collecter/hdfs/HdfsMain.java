package com.ehl.stdp.collecter.hdfs;

import com.ehl.stdp.collecter.AbstractTest;
import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.CheckConnection;
import com.ehl.stdp.collecter.SizeCollecter;

/**
 * Created by 雷晓武 on 2017/4/12.
 */
public class HdfsMain extends AbstractTest {
    @Override
    public void testCheck() throws Exception {
        CheckConnection check = new HdfsCheck();
        boolean isConnected = check.isConnectioned();
        System.out.println(isConnected);
        assert isConnected :"connect error";
    }

    @Override
    public void testSize() throws Exception {
        SizeCollecter sizeCollecter = new HdfsSize();
        long size =  sizeCollecter.getSize("/app/pathOfOften/2017-01-21");
        System.out.println(size);
        assert size>0 :"files size =0";
    }

    @Override
    public void testCapacity() throws Exception {
        CapacityCollecter collecter = new HdfsCollecter();
        String size  = collecter.getCapacityByName("/app");
        System.out.println(size);
        assert !size.isEmpty():"null...";
    }

    public static void main(String[]args) throws Exception {
        new HdfsMain().test();
    }
}
