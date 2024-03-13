package org.fwx.d02_servlet.d4dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName D4Servlet1
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/3 21:25
 * @Version 1.0
 */
public class D4Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("servlet2 username = " + username);

        String servlet1 = (String) req.getAttribute("servlet1");
        System.out.println("servlet2 get data. servlet1 = " + servlet1);
    }
}
