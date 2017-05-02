package com.ehl.stdp.collecter;

/**
 * Created by 雷晓武 on 2017/3/27.
 */
public interface CheckConnection extends EhlConnection{
    /**
     *
     * @return
     *
     * 链接判断
     *
     */
    public boolean isConnectioned() throws Exception;

    /**
     *
     * @return
     * 链接信息
     */
    public String getSummary();
}
