package org.fwx.d07_jdbc.mysql;

import org.fwx.d07_jdbc.mysql.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName GeneralUpdate
 * @Description 通用更新方法
 * @Author Fwx
 * @Date 2024/4/2 15:50
 * @Version 1.0
 */
public class GeneralUpdate {

    /**
     * 单条更新 sql 执行操作，不可配合事务
     * @param sql
     * @param args
     */
    public static void update(String sql,Object ... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtils.getConnection();

            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(ps);
            JdbcUtils.close(connection);
        }
    }

    /**
     * 单条更新 sql 执行操作，connection 外部传入，调用方控制事务的提交，可配合事务
     * @param connection
     * @param sql
     * @param args
     */
    public static void updateTx(Connection connection,String sql,Object ... args) {
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(ps);
        }
    }
}
