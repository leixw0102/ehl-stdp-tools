package com.ehl.stdp.collecter.hdfs;

import avro.shaded.com.google.common.collect.Lists;
import com.hadoop.compression.lzo.LzopCodec;
import com.hadoop.compression.lzo.LzopDecompressor;
import com.hadoop.compression.lzo.LzopInputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.parquet.Strings;
import org.apache.parquet.hadoop.Footer;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by 雷晓武 on 2017/4/12.
 */
public enum Compression {
    NONE {
        @Override
        public long getSizeLong(FileSystem fs, Path path, String filter) throws IOException {
            BufferedReader in=null;
            long c =0;
            System.out.println(path.toString()+" read file");
            try {
                in = new BufferedReader(new InputStreamReader(fs.open(path)));

                String line;
                while ( (line = in.readLine())!=null){
                    c++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(null != in){
                    in.close();
                }
            }
            return c;
        }
    },LZO {
        @Override
        public long getSizeLong(FileSystem fs, Path path, String filter) throws IOException {
            BufferedReader in=null;
            long c =0;
            System.out.println(path.toString()+" read file");
            try {
                LzopCodec lzoCodec = new LzopCodec();
                lzoCodec.setConf(fs.getConf());
//                 CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(Class.forName("com.hadoop.compression.lzo.LzoCodec"), fs.getConf());
                in = new BufferedReader(new InputStreamReader(lzoCodec.createInputStream(fs.open(path))));

                String line;
                while ( (line = in.readLine())!=null){
                    c++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(null != in){
                    in.close();
                }
            }
            return c;
        }
    },PARQUET {
        @Override
        public long getSizeLong(FileSystem fs, Path path, String filter) throws IOException {
            ParquetFileReader reader = ParquetFileReader.open(fs.getConf(),path);
            return (int)reader.getRecordCount();
        }
    };


    public abstract long getSizeLong(FileSystem fs, Path path, String filter) throws IOException;

    public long getSizeLong(FileSystem fs,Path path) throws IOException{
        return  getSizeLong(fs,path,null);
    }

    public static long execute(FileSystem fileSystem, FileStatus status) throws IOException {
        String name=status.getPath().getName();
        String suffix =  name.substring(name.lastIndexOf(".")+1);
        Compression c = NONE;
        if( !suffix.equals(name)){
            try {
                c = valueOf(suffix.toUpperCase());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println(c.name());
        return c.getSizeLong(fileSystem,status.getPath());

    }
}
