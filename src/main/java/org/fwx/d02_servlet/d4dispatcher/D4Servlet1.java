package org.fwx.d02_servlet.d4dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName D4Servlet1
 * @Description 请求转发的特点
 *                  1.是一次请求，浏览器地址不变
 *                  2.可以共享 Request 域中的数据
 *                  3.可以转发访问 WEB-INFO 目录的数据
 *                  4.不能访问外部工程
 * @Author Fwx
 * @Date 2024/3/3 21:25
 * @Version 1.0
 */
public class D4Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("servlet1 username = " + username);

        req.setAttribute("servlet1","servlet1 pass");

        // 转发请求
        RequestDispatcher dispatcher = req.getRequestDispatcher("/d4Servlet2");
        dispatcher.forward(req,resp);
    }
}
