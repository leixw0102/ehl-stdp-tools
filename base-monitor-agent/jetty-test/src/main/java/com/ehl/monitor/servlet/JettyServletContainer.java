package com.ehl.monitor.servlet;

import com.ehl.monitor.AbstractJetty;
import com.ehl.monitor.BaseConstant;
import com.ehl.monitor.BaseListener;
import com.ehl.monitor.BaseServletMonitor;

/**
 * Created by 雷晓武 on 2017/6/6.
 */
public class JettyServletContainer extends AbstractJetty {


    public JettyServletContainer() {

    }




    public static void main(String[]args) throws Exception {
        JettyServletContainer jettyServletContainer = new JettyServletContainer();
        jettyServletContainer.setContextPath("/");
        jettyServletContainer.addMonitorEventListener(new BaseListener());
        jettyServletContainer.setInitParameter(BaseConstant.BASE_APP_NAME_KEY,"ehl-test");
        jettyServletContainer.addServlet(BaseServletMonitor.class,"/appMonitor");
        jettyServletContainer.start();
    }

}
