package org.fwx.d02_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName D5ResponseServlet
 * @Description Response
 *              1、响应字符集
 * @Author Fwx
 * @Date 2024/3/3 22:51
 * @Version 1.0
 */
public class D5ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理返回中文乱码问题
        // 方式一
//        resp.setCharacterEncoding("utf-8");
//        resp.setHeader("Content-Type","text/html; charset=UTF-8");
        // 方式二 （必须在获取输出对象之前）
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("湖人总冠军！");
    }
}
