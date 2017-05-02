package com.ehl.stdp.collecter.hdfs;

import com.ehl.stdp.collecter.SizeCollecter;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 雷晓武 on 2017/4/12.
 */
public class HdfsSize extends AbstractHdfsConnection implements SizeCollecter {

    @Override
    public long getSize(String table) throws Exception {

        FileSystem fileSystem = null;

        try {
            fileSystem=FileSystem.get(conf);
            iteratorPath(fileSystem,new Path(table),"");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != fileSystem){
                fileSystem.close();
            }
        }

        return count.get();
    }
    AtomicLong count = new AtomicLong(0);
    private void iteratorPath(FileSystem fileSystem, Path path, String suffix) {
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(path);
            for(FileStatus status : fileStatuses){
                if(status.isDirectory()){
                    System.out.println(">>>" + status.getPath() + ", dir owner:" + status.getOwner());
                    iteratorPath(fileSystem,status.getPath(),suffix);
                }else if(status.isFile()){
                    if(status.getPath().getName().startsWith("_")){
                        continue;
                    }
                    long size = Compression.execute(fileSystem,status);
                    System.out.println(size+"\t"+count.get());
                    count.addAndGet(size);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) throws Exception {
//       long size =  new HdfsSize("").getSize("/app/base/bay_pair/2017-01-21");
//        long size =  new HdfsSize("").getSize("/app/pathOfOften/2017-01-21/");
        long size =  new HdfsSize().getSize("/2016-11-28/");
        System.out.println(size);
//        String s="part-000000";
//        System.out.println(s.indexOf(".")+1);
//    size.test();
    }
}
