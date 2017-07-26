package jdk.buffer;

import java.nio.ByteBuffer;

/**
 * Created by 雷晓武 on 2017/3/27.
 */
public class BufferExample {

    public static void main(String[]args){
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.putInt(4);
//        buffer.put("abc".getBytes());
        buffer.putLong(10L);

        buffer.flip();
        buffer.rewind();
        System.out.println(buffer.getInt());
//        System.out.println(buffer.get());
        System.out.println(buffer.getLong());
//                buffer.clear();

    }

}
