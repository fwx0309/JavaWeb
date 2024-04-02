package org.fwx.d07_jdbc.mysql;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName D01_getConnection
 * @Description TODO
 * @Author Fwx
 * @Date 2024/4/1 22:41
 * @Version 1.0
 */
public class D01GetConnection {

    @Test
    public void getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = D01GetConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        // 1.初始化 mysql 驱动类
        Class.forName(properties.getProperty("mysql_driver"));

        // 2.获取 connection
        String user = properties.getProperty("mysql_user");
        String passwd = properties.getProperty("mysql_passwd");
        String url = properties.getProperty("mysql_url");
        Connection connection = DriverManager.getConnection(url, user, passwd);

        System.out.println("connection = " + connection);
    }
}
