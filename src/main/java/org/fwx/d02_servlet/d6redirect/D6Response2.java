package org.fwx.d02_servlet.d6redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName D6Response1
 * @Description 重定向
 * @Author Fwx
 * @Date 2024/3/4 10:23
 * @Version 1.0
 */
public class D6Response2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("****** Response2 ******");
        PrintWriter writer = resp.getWriter();
        writer.write("this is response2");
    }
}
