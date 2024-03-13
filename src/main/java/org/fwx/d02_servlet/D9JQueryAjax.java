package org.fwx.d02_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName D9JQueryAjax
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/10 12:47
 * @Version 1.0
 */
public class D9JQueryAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        System.out.println("name = " + name);

        PrintWriter writer = resp.getWriter();
        writer.write("{\"name\":\"fwxNew\"}");
    }
}
