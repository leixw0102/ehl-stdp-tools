package com.ehl.stdp.collecter.es;

import com.ehl.stdp.collecter.*;

/**
 * Created by 雷晓武 on 2017/3/24.
 */
public class EsMain  extends AbstractTest {
    @Override
    public void testCheck() throws Exception {
        CheckConnection check = new EsCheck();
        boolean isConnected =  check.isConnectioned();
        System.out.println(isConnected);
        assert isConnected:"es connection closed";
    }
    @Override
    public void testSize() throws Exception{
        SizeCollecter size = new EsCount();
        long  size1= size.getSize("ehlindex");
        long size2= size.getSize("example");
        System.out.println(size1+"\t"+size2);
        assert size1>0 :"ehlindex : the index of es, size =0";
        assert size2>0 :"example : the index of es, size =0";
    }
    @Override
    public void testCapacity() throws Exception {
        CapacityCollecter capacity = new EsCollecter();
        String c1 = capacity.getCapacityByName("ehlindex");
        String c2 = capacity.getCapacityByName("example");
        System.out.println(c1+"\t"+c2);
        assert !c1.isEmpty() && !c2.isEmpty() :"capacity is null";
    }


    public static void main(String[]args) throws Exception {
        //check
        EsMain esMain = new EsMain();
        esMain.test();
        //
    }
}
