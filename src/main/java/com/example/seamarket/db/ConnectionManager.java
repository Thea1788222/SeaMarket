package com.example.seamarket.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConnectionManager {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(in);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

            Class.forName(driver); // 加载驱动
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 测试连接是否成功
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        System.out.println("连接成功：" + conn.isValid(2)); // true 表示连接成功
        conn.close();
    }
}
