package com.qaprocess.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
public class AuthController{
    @RequestMapping("/authentication/**")
    public void authentication(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path =req.getRequestURI();
//        获取输入的源地址
        path = path.substring(path.indexOf("authentication")+14);


        resp.setHeader("Content-Type", "application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        Map map=new HashMap<String, Object>();
        map.put("code",2020);
        ObjectMapper objectMapper=new ObjectMapper();
        String s=objectMapper.writeValueAsString(map);
        resp.getWriter().write(s);
        resp.getWriter().flush();
        resp.getWriter().close();



    }

}
/*@Controller
@RequestMapping("/authentication/**")
@WebServlet("/authentication/**")*/
/*
public class AuthController extends HttpServlet {

    */
/**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>Method</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link Servlet#service} method. There's no
     * need to override this method.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request
     *                          cannot be handled
     * @see Servlet#service
     *//*

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        log.warn("开始getRequestURI:",req.getRequestURI());
    }
}
*/
