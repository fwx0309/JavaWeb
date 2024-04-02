package org.fwx.d07_jdbc.mysql;

import org.fwx.d07_jdbc.mysql.bean.Customer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 * @ClassName GeneralQuery
 * @Description JDBC 通查询工具
 * @Author Fwx
 * @Date 2024/4/2 9:37
 * @Version 1.0
 */
public class GeneralQuery {

    /**
     * 查询接口：将查询到的结果集，封装到传入的实体类对象集合中返回
     * @param clazz 封装数据的实体类
     * @param sql 查询 sql
     * @param args 对应 sql 中的查询参数列表
     * @return
     * @param <T>
     */
    public static <T> ArrayList<T> queryCustomer(Class<T> clazz,String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = JdbcUtils.getConnection();

            ps = connection.prepareStatement(sql);
            // 设置预编译 sql 的参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            // 返回结果集
            ArrayList<T> objs = new ArrayList<>();
            while (rs.next()) {
                // 当前需要封装的对象
                T t = clazz.newInstance();

                // 获取表元数据
                ResultSetMetaData metaData = rs.getMetaData();

                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    // 获取查询结果中的数据
                    Object object = rs.getObject(i);

                    // 反射给对象赋值
                    // 这里使用列别名，不用 getColumnName()，避免数据库表字段和实体类的属性名不一致问题。
                    String columnName = metaData.getColumnLabel(i);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                objs.add(t);
            }

            return objs;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(ps);
            JdbcUtils.close(connection);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = queryCustomer(Customer.class,"SELECT id,name,email,birth FROM customers WHERE id > ?", 1);
        System.out.println("customer = " + customers);
    }
}
