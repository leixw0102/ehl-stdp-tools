package com.ehl.stdp.phoenix.sql;

import java.sql.Connection;


/**
 * Created by 雷晓武 on 2017/3/8.
 */
public interface Callback<T> {
     T call(Connection conn) throws Exception;
}
