
package com.practice.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC接口的Connection代表一个JDBC连接；
 * 使用JDBC查询时，总是使用PreparedStatement进行查询而不是Statement；
 * 查询结果总是ResultSet，即使使用聚合查询也不例外。
 */
public class JdbcQueryDemo {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL="jdbc:mysql://localhost:3306/learnjdbc";
        String JDBC_USER="root";
        String JDBC_PASSWORD="root";
        try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(Statement stmt=conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=\'M\'")){
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        String gender = rs.getString(4);
                        System.out.println(name);
                    }
                }
            }
        }
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps=conn.prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")){
                ps.setObject(1, "M"); // 注意：索引从1开始
                ps.setObject(2, 3);
                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        String gender = rs.getString(4);
                        System.out.println(name);
                    }
                }
            }
        }
    }
}