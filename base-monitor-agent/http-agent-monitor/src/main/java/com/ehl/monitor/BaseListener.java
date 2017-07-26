package com.ehl.monitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * Created by 雷晓武 on 2017/6/6.
 * 增加启动时间，判断程序是否正常运行。
 */
public class BaseListener implements ServletContextListener ,BaseConstant{


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        long start_time = new Date().getTime();
        String appName = sce.getServletContext().getInitParameter(BASE_APP_NAME_KEY);
        sce.getServletContext().setAttribute(BASE_START_TIME_KEY,start_time);
        String value = BASE_APP_DEFAULT_VALUE;
        if(null != appName){
            value = appName;
        }
        sce.getServletContext().setAttribute(BASE_APP_NAME_KEY,value);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("base monitor is destoryed");
        sce.getServletContext().removeAttribute(BASE_START_TIME_KEY);
    }
}
