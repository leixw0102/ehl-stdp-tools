package jdk.net.xdevelop.timeserver;

import jdk.net.xdevelop.nioserver.Request;
import jdk.net.xdevelop.nioserver.event.EventAdapter;

import java.util.Date;

/**
 * ��־��¼
 */
public class LogHandler extends EventAdapter {
    public LogHandler() {
    }

    public void onClosed(Request request) throws Exception {
        String log = new Date().toString() + " from " + request.getAddress().toString();
        System.out.println(log);
    }

    public void onError(String error) {
        System.out.println("Error: " + error);
    }
}
