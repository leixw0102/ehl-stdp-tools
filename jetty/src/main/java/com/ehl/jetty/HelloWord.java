package com.ehl.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 雷晓武 on 2017/5/4.
 */
public class HelloWord extends AbstractHandler {
    final String greeting;

    public HelloWord()
    {
        this("Hello");
    }

    public HelloWord( String greeting )
    {
        this.greeting = greeting;
    }

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
        // Declare response encoding and types
        response.setContentType("text/html; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);

        // Write back response
        response.getWriter().println("<h1>Hello World</h1>");

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }

    public static void main(String[]args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new HelloWord());

        server.start();
        server.join();
    }

}
