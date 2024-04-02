package org.fwx.d07_jdbc.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JdbcUtils
 * @Description TODO
 * @Author Fwx
 * @Date 2024/4/2 8:07
 * @Version 1.0
 */
public class JdbcUtils {

    /**
     * 获取数据库连接
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        // 1.初始化 mysql 驱动类
        Class.forName(properties.getProperty("mysql_driver"));

        // 2.获取 connection
        String user = properties.getProperty("mysql_user");
        String passwd = properties.getProperty("mysql_passwd");
        String url = properties.getProperty("mysql_url");
        Connection connection = DriverManager.getConnection(url, user, passwd);

        return connection;
    }

    /**
     * 资源释放
     * @param t
     * @param <T>
     */
    public static  <T> void close(T t){
        // connaction 关闭
        if (t instanceof Connection && t != null) {
            try {
                ((Connection) t).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Statement 关闭
        if (t instanceof Statement && t != null) {
            try {
                ((Statement) t).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (t instanceof ResultSet && t != null) {
            try {
                ((ResultSet) t).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
