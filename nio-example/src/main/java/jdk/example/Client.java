package jdk.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
public class Client {
    public static void main(String[]args) throws IOException {
        new Client().start();
    }
    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private void start() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
//        System.out.println(channel.isConnected()+"\t"+channel.isConnectionPending());
        Selector s = Selector.open();
        channel.register(s, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress("localhost",8888));
//        Scanner scanner = new Scanner(System.in);
        while (true){
            s.select();
            Iterator<SelectionKey> it = s.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();
                if(key.isConnectable() ){
                    SocketChannel client = (SocketChannel) key.channel();
                    System.out.println("..ss"+client.isConnectionPending()+"\t"+client.isConnected());

                    if(client.isConnectionPending()){
                        channel.finishConnect();
                        System.out.println("完成连接!");
                        writeBuffer.clear();
                        writeBuffer.put("Hello,Server".getBytes());
                        writeBuffer.flip();
                        client.write(writeBuffer);
//                        client.close();
                    }
                    channel.register(s,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    SocketChannel client = (SocketChannel) key.channel();
                    //将缓冲区清空以备下次读取
                    readBuffer.clear();
                    //读取服务器发送来的数据到缓冲区中
                    int count=client.read(readBuffer);
                    if(count>0){
                        String  receiveText = new String( readBuffer.array(),0,count);
                        System.out.println("客户端接受服务器端数据--:"+receiveText);
                        client.register(s, SelectionKey.OP_WRITE);
                    }
                }else if(key.isWritable()){
                    writeBuffer.clear();
                    SocketChannel client = (SocketChannel) key.channel();
                    String sendText = "message from client--" + (new Random(10).nextInt());
                    writeBuffer.put(sendText.getBytes());
                    //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                    writeBuffer.flip();
                    client.write(writeBuffer);
                    System.out.println("客户端向服务器端发送数据--："+sendText);
                    client.register(s, SelectionKey.OP_READ);
                }
//                it.remove();
            }

        }

    }
}
