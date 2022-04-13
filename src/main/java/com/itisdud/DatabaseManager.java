package com.itisdud;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement statement;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:./db/thinclient.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            statement.executeUpdate("drop table if exists user");
            statement.executeUpdate("create table user(id integer primary key, name string, username string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(Account account) {
        try {
            statement.executeUpdate("insert into user values(" + account.getId() + ", '" + account.getName() + "', '" + account.getUsername() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getUser(String username) {
        try {
            ResultSet rs = statement.executeQuery("select * from user where username='" + username + "'");
            if (rs.next()) {
                return new Account(rs.getString("name"), rs.getString("id"), rs.getString("username"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
