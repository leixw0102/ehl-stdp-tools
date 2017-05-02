package com.ehl.service;

import javax.jws.WebService;

/**
 * Created by 雷晓武 on 2017/4/24.
 */
@WebService
public interface HelloService {

    String say(String msg);
}
