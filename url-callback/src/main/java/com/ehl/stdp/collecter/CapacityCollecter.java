package com.ehl.stdp.collecter;


/**
 * Created by 雷晓武 on 2017/3/23.
 */
public interface CapacityCollecter {

    public String getTotalCapacity() throws Exception;

    public String getCapacityByName(String file) throws Exception;

    public String percentageByName(String name);

}
