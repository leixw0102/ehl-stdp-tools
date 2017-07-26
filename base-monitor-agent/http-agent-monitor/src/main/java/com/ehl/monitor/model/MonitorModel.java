package com.ehl.monitor.model;

import java.io.Serializable;

/**
 * Created by 雷晓武 on 2017/6/6.
 */
public class MonitorModel implements Serializable {
    private long startTime;
    private int code;
    private String msg;
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public MonitorModel() {
        this.code=200;
        this.msg="SUCCESS";
    }

    public MonitorModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
