package com.ehl.stdp.collecter;


import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public abstract class AbstractConnection<T> implements CheckConnection {

    @Override
    public boolean isConnectioned() throws Exception {
        return false;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public void init() throws IOException {
        throw new NullPointerException("nothing");
    }


    @Override
    public void close() {
        throw new NullPointerException("nothing");
    }

    /**
     * 链接回调，执行业务主要方法
     * 注意处理分布式时需要加同步关键字
     * @param callBack
     * @param <O>
     * @return
     * @throws Exception
     */
    public  abstract  <O> O execute(CallBack<T,O> callBack) throws Exception;
}
