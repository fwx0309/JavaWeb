package org.fwx.d02_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName D7Cookie
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/8 17:04
 * @Version 1.0
 */
public class D7Cookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("c1","c1test");
        // 设置超时时间
        // 正数：存活时间秒
        // 0：立即删除
        // 负数：浏览器关闭时删除
        cookie.setMaxAge(10);
        resp.addCookie(cookie);

        Cookie cookie1 = new Cookie("c2","c2test");
        resp.addCookie(cookie1);

        // 获取 cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookieObj : cookies) {
            System.out.println("cookieObj = " + cookieObj.getName() +":" + cookieObj.getValue());
        }
    }
}
