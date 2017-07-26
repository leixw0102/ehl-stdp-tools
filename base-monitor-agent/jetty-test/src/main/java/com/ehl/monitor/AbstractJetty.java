package com.ehl.monitor;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.http.HttpServlet;
import java.util.EventListener;

/**
 * Created by 雷晓武 on 2017/6/6.
 */
public class AbstractJetty  {

    Server server = null;
    ServletContextHandler servletContextHandler;

    public AbstractJetty(){
        this(8080);
    }

    public AbstractJetty(int port){
        server = new Server(port);
        servletContextHandler = new ServletContextHandler();
    }
    public void setInitParameter(String name,String value){
        servletContextHandler.setInitParameter(name,value);
    }



    public void addMonitorEventListener(EventListener listener){
        servletContextHandler.addEventListener(listener);
    }
    public void setContextPath(String path){
        servletContextHandler.setContextPath(path);
    }

    public  void addServlet(Class<?extends HttpServlet> cls, String path){
        servletContextHandler.addServlet(cls,path);
    }

    public void start(){
        try {
            HandlerCollection handlers = new HandlerCollection();
            handlers.setHandlers(new Handler[]{servletContextHandler,new DefaultHandler()});
            server.setHandler(handlers);
            server.join();
            server.setStopAtShutdown(true);
            server.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
