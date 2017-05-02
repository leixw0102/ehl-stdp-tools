package com.ehl.stdp.collecter.mysql;

import com.ehl.stdp.collecter.AbstractTest;
import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.CheckConnection;
import com.ehl.stdp.collecter.SizeCollecter;
import org.apache.parquet.Strings;

/**
 * Created by 雷晓武 on 2017/4/13.
 */
public class MysqlMain extends AbstractTest {
    @Override
    public void testCheck() throws Exception {
        CheckConnection check = new MysqlCheck();
        boolean c = check.isConnectioned();
        System.out.println(c);
        assert c;
    }

    @Override
    public void testSize() throws Exception {
        SizeCollecter collecter = new MysqlSize();
        long size = collecter.getSize("t_tiap_admin_division");
        System.out.println(size);
        assert size>0;
    }

    @Override
    public void testCapacity() throws Exception {
        CapacityCollecter collecter = new MySqlCollecter();
        String value = collecter.getCapacityByName("t_tiap_admin_division");
        System.out.println(value);
        assert !Strings.isNullOrEmpty(value);
    }

    public static void main(String []args) throws Exception {
        new MysqlMain().test();
    }
}
