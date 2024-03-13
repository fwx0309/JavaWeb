package org.fwx.d02_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName D3HttpServletRequest
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/3 17:23
 * @Version 1.0
 */
public class D3HttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        StringBuffer requestURL = req.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        String header = req.getHeader("User-Agent");
        System.out.println("header = " + header);

        // 获取数据
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        String password = req.getParameter("password");
        System.out.println("password = " + password);

        String[] hobis = req.getParameterValues("hobi");
        System.out.println("hobis = " + Arrays.asList(hobis));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理数据中文乱码
        req.setCharacterEncoding("utf-8");

        // 获取数据
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        String password = req.getParameter("password");
        System.out.println("password = " + password);

        String[] hobis = req.getParameterValues("hobi");
        System.out.println("hobis = " + Arrays.asList(hobis));
    }
}
