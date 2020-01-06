package com.practice.jdbcdemo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 数据库连接池是一种复用Connection的组件，它可以避免反复创建新连接，提高JDBC代码的运行效率； 可以配置连接池的详细参数并监控连接池。
 */
public class JdbcConnectionPoolDemo {

    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        config.setUsername("root");
        config.setPassword("password");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        try (Connection conn = ds.getConnection()) { // 在此获取连接
            
        } // 在此“关闭”连接
    }
}