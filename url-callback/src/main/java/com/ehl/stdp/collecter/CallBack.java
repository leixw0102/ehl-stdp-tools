package com.ehl.stdp.collecter;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public interface CallBack<I,O> {

    public O execute(I i)throws Exception;
}
