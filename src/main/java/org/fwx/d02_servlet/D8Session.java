package org.fwx.d02_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName D8Session
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/9 12:20
 * @Version 1.0
 */
public class D8Session extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // session 超时
        // 正数：秒数
        // 负数：用不超时
        boolean aNew = session.isNew();
        System.out.println("aNew = " + aNew);

        // 立即超时
        // session.invalidate();

        session.setAttribute("sessionTest","sessionTest");

        Object sessionTest = session.getAttribute("sessionTest");

        System.out.println("sessionTest = " + sessionTest);

    }
}
