package jdk.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
public class Server {
    /*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private Selector selector;

    public Server(int i) {
        ServerSocketChannel channel =null;
        try {
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(i));
            //ServerSocket serverSocket = channel.socket();
            //serverSocket.bind(new InetSocketAddress(i));
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("accept...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args){
        Server server = new Server(8888);
        server.listen();
    }

    private void listen() {
        while (true){
            try {
                selector.select();

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();
                    handerKey(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handerKey(SelectionKey key) throws IOException {
        // 接受请求
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count=0;
        try {
            if (key.isAcceptable()) {
                System.out.println("accept..");
                ServerSocketChannel s = (ServerSocketChannel) key.channel();
                client = s.accept();
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
                System.out.println("a new client connected "+client.getRemoteAddress());
                ;                //buffer

            } else if (key.isReadable()) {
                System.out.println("read..");
                client = (SocketChannel) key.channel();
                receivebuffer.clear();
                //读取服务器发送来的数据到缓冲区中
                count = client.read(receivebuffer);
                if (count > 0) {
                    receiveText = new String( receivebuffer.array(),0,count);
                    System.out.println("服务器端接受客户端数据--:"+receiveText);
                    client.register(selector, SelectionKey.OP_WRITE);
                }
            } else if (key.isWritable()) {
                sendbuffer.clear();
                client = (SocketChannel) key.channel();
                sendText = "message from server--" + flag++;
                sendbuffer.put(sendText.getBytes());
                sendbuffer.flip();
                client.write(sendbuffer);
                System.out.println("服务器端向客户端发送数据--：" + sendText);
                client.register(selector, SelectionKey.OP_READ);
            } else if(key.isConnectable()){
                System.out.println("....");
            }
        }catch (Exception e){
            e.printStackTrace();
            key.cancel();
            client.close();
        }finally {
//            if(null != client){
//                client.close();
//            }
        }
    }
}
