package com.ehl.stdp.collecter.hdfs;

import com.ehl.stdp.collecter.CapacityCollecter;
import com.ehl.stdp.collecter.TraditionalBinaryPrefix;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/3/23.
 */
public class HdfsCollecter extends AbstractHdfsConnection implements CapacityCollecter {



    @Override
    public String getTotalCapacity() throws Exception {
        return getCapacityByName("/");
    }

    @Override
    public String getCapacityByName(String file) throws Exception {
        FileSystem fs = null;
        try{
            fs = FileSystem.get(conf);
            if(fs.exists(new Path(file))) {
                ContentSummary cs = fs.getContentSummary(new Path(file));
                return TraditionalBinaryPrefix.formatSize(cs.getLength(), true);
            }else{
                throw new Exception("hdfs path=+"+file+" does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "NAN";
        } finally{
            if(null != fs ){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String percentageByName(String name) {
//        Double.
        return "NAN";
    }
    public static void main(String[]args) throws Exception {
        System.out.println(new HdfsCollecter().getCapacityByName("/app"));
    }


}
