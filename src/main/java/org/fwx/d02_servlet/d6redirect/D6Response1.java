package org.fwx.d02_servlet.d6redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName D6Response1
 * @Description 重定向
 *              1、两次请求，浏览器地址会变化
 *              2、不共享 Request 域对象
 *              3、不能访问 WEB-INOF 中的资源
 *              4、可以访问外部资源
 * @Author Fwx
 * @Date 2024/3/4 10:23
 * @Version 1.0
 */
public class D6Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("****** Response1 ******");
        // 方式一
//        resp.setStatus(302);
//        resp.setHeader("Location","http://localhost:8080/JavaWeb/d6Response2");
        // 方式二
        resp.sendRedirect("http://localhost:8080/JavaWeb/d6Response2");
    }
}
