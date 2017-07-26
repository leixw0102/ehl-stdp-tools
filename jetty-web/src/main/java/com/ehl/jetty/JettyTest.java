package com.ehl.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * Created by 雷晓武 on 2017/5/12.
 */
public class JettyTest {
    public static void main(String[]args){
        Server server = new Server(8080);
//        MBeanContainer mbContainer = new MBeanContainer(
//                ManagementFactory.getPlatformMBeanServer());
//        server.addBean(mbContainer);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        File warFile = new File(
                "../../tests/test-jmx/jmx-webapp/target/jmx-webapp");
        webapp.setWar(warFile.getAbsolutePath());
    }
}
