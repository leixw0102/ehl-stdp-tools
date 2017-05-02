package jdk.channels;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 *
 * Created by 雷晓武 on 2017/3/27.
 */
public class FileExample {
    //new FileInputStream("").getChannel();
    //new FileOutputStream("").getChannel()

    public void exampleFileChannel() throws FileNotFoundException {
        RandomAccessFile asf=new RandomAccessFile("","rw");

        //


        try {
            asf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) throws FileNotFoundException {

    }
}
