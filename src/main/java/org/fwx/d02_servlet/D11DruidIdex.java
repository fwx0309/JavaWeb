package org.fwx.d02_servlet;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.fwx.d07_jdbc.mysql.JdbcUtils;
import org.fwx.d07_jdbc.mysql.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @ClassName D11DruidIdex
 * @Description TODO
 * @Author Fwx
 * @Date 2024/4/28 14:55
 * @Version 1.0
 */
public class D11DruidIdex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = null;
            try {
                connection = JdbcUtils.getConnectionByDruid();
                String sql = "select id,name,email,birth from customers where id = ?";
                ResultSetHandler<Customer> rsh = new BeanHandler<>(Customer.class);
                Customer query = queryRunner.query(connection, sql, rsh, 20);
                System.out.println("D11DruidIdex query: " + query);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.close(connection);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
