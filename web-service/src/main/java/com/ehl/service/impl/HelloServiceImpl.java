package com.ehl.service.impl;

import com.ehl.service.HelloService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
@WebService(name = "hello",endpointInterface = "com.ehl.service.HelloService")
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String msg) {
        return null;
    }
}
