package org.fwx.d07_jdbc.mysql;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName D02PreparedStatement
 * @Description mysql 数据库，增删改查。
 * @Author Fwx
 * @Date 2024/4/1 23:22
 * @Version 1.0
 */
public class D02PreparedStatement {

    private String user = null;
    private String passwd = null;
    private String url = null;
    private String driver = null;

    @Before
    public void before() throws Exception {
        InputStream is = D01GetConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        user = properties.getProperty("mysql_user");
        passwd = properties.getProperty("mysql_passwd");
        url = properties.getProperty("mysql_url");
        driver = properties.getProperty("mysql_driver");
    }

    /**
     * 插入数据测试
     * @throws Exception
     */
    @Test
    public void insertCustomer() throws Exception {
        Class.forName(driver);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, user, passwd);

            String sql = "INSERT INTO customers (name,email) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"雷神");
            preparedStatement.setString(2,"leishen@gmail.com");

            preparedStatement.execute();

            System.out.println("insert success!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null)
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 更新数据
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void updateCustomer() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcUtils.getConnection();

        String sql = "UPDATE customers SET birth = NOW() WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,19);

        preparedStatement.execute();

        System.out.println("update success!");

        JdbcUtils.close(preparedStatement);
        JdbcUtils.close(connection);
    }

    /**
     * 没有事务控制，操作两个数据
     */
    @Test
    public void generalUpdateTest(){
        int num = 100;
        GeneralUpdate.update("UPDATE user_table SET balance=balance-? WHERE `user`=?;",num,"AA");
        // int a = 1/0;
        GeneralUpdate.update("UPDATE user_table SET balance=balance+? WHERE `user`=?;",num,"BB");
    }

    /**
     * 有事务控制，操作两个数据
     */
    @Test
    public void generalUpdateTxTest() {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            int num = 100;
            GeneralUpdate.updateTx(connection,"UPDATE user_table SET balance=balance-? WHERE `user`=?;",num,"AA");
            // int a = 1/0;
            GeneralUpdate.updateTx(connection,"UPDATE user_table SET balance=balance+? WHERE `user`=?;",num,"BB");
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
    }
}
