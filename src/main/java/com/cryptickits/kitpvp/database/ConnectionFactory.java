package com.cryptickits.kitpvp.database;

import java.sql.*;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private final String DATABASE_PASSWORD = "root";
    private final String DATABASE_USERNAME = "root";

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws Exception {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public Connection getConnection(String tableName) throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL + tableName, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

}
