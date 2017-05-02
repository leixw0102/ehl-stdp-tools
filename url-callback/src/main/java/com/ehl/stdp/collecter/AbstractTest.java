package com.ehl.stdp.collecter;

/**
 * Created by 雷晓武 on 2017/4/12.
 */
public abstract class AbstractTest implements TestVerification{


    @Override
    public void test() throws Exception {
        testCheck();
        testCapacity();
        testSize();
    }
}
