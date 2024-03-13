package org.fwx.d02_servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName D2GetOrPost
 * @Description 继承 HttpServlet 类
 * @Author Fwx
 * @Date 2024/3/2 16:04
 * @Version 1.0
 */
public class D2GetOrPostServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 这里不调用父类 init() 方法，会报空指针问题
        super.init(config);
        System.out.println("重写了父类的 init 方法");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get ......");

        // *** 获取 ServletConfig 对象
        ServletConfig servletConfig = getServletConfig();
        System.out.println("servletConfig = " + servletConfig);

        // 获取 init 参数
        String username = servletConfig.getInitParameter("username");
        System.out.println("username = " + username);
        String url = servletConfig.getInitParameter("mysql");
        System.out.println("url = " + url);

        // *** 获取 ServletContext 对象及相关操作
        ServletContext servletContext = servletConfig.getServletContext();

        String username1 = servletContext.getInitParameter("username");
        System.out.println("username1 = " + username1);
        String age = servletContext.getInitParameter("age");
        System.out.println("age = " + age);

        String contextPath = servletContext.getContextPath();
        System.out.println("contextPath = " + contextPath);

        String realPath = servletContext.getRealPath("/Hello.html");
        System.out.println("realPath = " + realPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post ......");
    }
}
