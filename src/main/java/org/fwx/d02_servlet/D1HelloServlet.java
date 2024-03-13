package org.fwx.d02_servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName D01HelloServlet
 * @Description 实现 Servlet 接口
 * @Author Fwx
 * @Date 2024/3/2 8:55
 * @Version 1.0
 */
public class D1HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 1.获取 servlet 别名
        String servletName = servletConfig.getServletName();
        System.out.println("servletName = " + servletName);

        // 2.获取初始化参数
        String username = servletConfig.getInitParameter("username");
        System.out.println("username = " + username);
        String url = servletConfig.getInitParameter("mysql");
        System.out.println("url = " + url);

        // 3.获取 servletContext 对象
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println("servletContext = " + servletContext);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("********** 执行了servlet **********");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
