/**
 * Created by 雷晓武 on 2017/6/26.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Producer producerThread = new Producer("leixw-test-2", true);
        producerThread.start();

        new Consumer("leixw-test-2").start();
        Thread.sleep(1000000L);
    }
}
