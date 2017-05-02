package com.ehl.stdp.collecter;

import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/4/1.
 */
public interface EhlConnection {
    /**
     * 链接初始化
     * @throws IOException
     */
    public void init() throws IOException;

    /**
     * 链接关闭
     */
    public void close();
}
