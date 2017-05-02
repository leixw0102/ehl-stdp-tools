package com.ehl.service.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
public class SpringStart {

    public static void main(String[]args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-core.xml");

    }
}
