package com.ehl.monitor;

import com.alibaba.fastjson.JSONObject;
import com.ehl.monitor.model.MonitorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 雷晓武 on 2017/6/6.
 */
public class BaseServletMonitor extends HttpServlet implements BaseConstant{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private void writeJson(HttpServletResponse response,String json){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
            logger.info("result={}",json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MonitorModel monitorModel = new MonitorModel();
        try {
            Object time = this.getServletContext().getAttribute(BASE_START_TIME_KEY);
            if (null == time) {
                monitorModel.setCode(500);
                monitorModel.setMsg("get start time =null");
            }else {
                monitorModel.setStartTime(Long.parseLong(time.toString()));
                monitorModel.setAppName(this.getServletContext().getAttribute(BASE_APP_NAME_KEY).toString());
            }

        }catch (Exception e){
            e.printStackTrace();
            monitorModel.setCode(500);
            monitorModel.setMsg(e.getMessage());
        }
        writeJson(resp, JSONObject.toJSONString(monitorModel));
    }
}
