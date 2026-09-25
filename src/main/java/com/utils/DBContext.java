package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Mật khẩu để trống hoặc sửa lại nếu máy bạn có pass root
            String url = "jdbc:mysql://localhost:3306/player_evaluation?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}