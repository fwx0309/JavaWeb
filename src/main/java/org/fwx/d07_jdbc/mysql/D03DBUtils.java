package org.fwx.d07_jdbc.mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.fwx.d07_jdbc.mysql.bean.Customer;
import org.junit.Test;

import java.sql.Connection;

/**
 * @ClassName D03DBUtils
 * @Description 数据库 CRUD 操作工具类
 *                  常用的查询 handler: BeanHandler、BeanListHandler、MapHandler、MapListHandler、
 *                            ScalarHandler(用于查询特殊值，如:count、max等结果)
 * @Author Fwx
 * @Date 2024/4/3 8:33
 * @Version 1.0
 */
public class D03DBUtils {
    /**
     * 新增一条数据
     */
    @Test
    public void insertCustomer(){
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnectionByDruid();
            String sql = "insert into customers (name,email,birth) values (?,?,?)";
            int num = queryRunner.update(connection, sql,"fwx","fwx@126.com","1989-03-09");
            System.out.println("insert: " + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
    }

    /**
     * 修改一条数据
     */
    @Test
    public void updateCustomer(){
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnectionByDruid();
            String sql = "update customers set birth = ? where id = ?";
            int num = queryRunner.update(connection, sql, "2000-01-01", 20);
            System.out.println("update: " + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
    }

    /**
     * 查询一条数据，封装到BeanHandler
     */
    @Test
    public void queryOneByBeanHandler(){
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnectionByDruid();
            String sql = "select id,name,email,birth from customers where id = ?";
            ResultSetHandler<Customer> rsh = new BeanHandler<>(Customer.class);
            Customer query = queryRunner.query(connection, sql, rsh, 20);
            System.out.println("query: " + query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
    }

    /**
     * 特殊值查询
     */
    @Test
    public void queryCount(){
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnectionByDruid();
            String sql = "select count(*) from customers";
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(connection, sql, scalarHandler);
            System.out.println("query: " + query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
    }
}
