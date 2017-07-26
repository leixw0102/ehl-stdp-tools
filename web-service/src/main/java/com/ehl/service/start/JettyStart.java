package com.ehl.service.start;

import com.ehl.service.HelloService;
import com.ehl.service.impl.HelloServiceImpl;
import org.eclipse.jetty.http.spi.DelegatingThreadPool;
import org.eclipse.jetty.http.spi.JettyHttpServerProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;


import javax.xml.ws.Endpoint;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
public class JettyStart {
    public static void main(String[]args) throws Exception {
        Server ws1 = new Server(new DelegatingThreadPool(new QueuedThreadPool()));
        ServerConnector connector = new ServerConnector(ws1);
        connector.setPort(8081);
        ws1.addConnector(connector);
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        ws1.setHandler(contexts);


        System.setProperty("com.sun.net.httpserver.HttpServerProvider", JettyHttpServerProvider.class.getName());

        HelloService service1 = new HelloServiceImpl();

        JettyHttpServerProvider.setServer(ws1);
        Endpoint.publish("http://0.0.0.0:8081/services/Service1", service1);

        ws1.start();
        ws1.join();
//        Thread.sleep(Long.MAX_VALUE);
    }
}
