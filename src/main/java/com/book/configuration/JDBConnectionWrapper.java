package com.book.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private static final int TIMEOUT = 5;

    private Connection connection;


    public JDBConnectionWrapper(String schemaName) {
        try {
            connection = DriverManager.getConnection(DB_URL + schemaName + "?useSSL=false", USER, PASS);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS book (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  title varchar(255) NOT NULL," +
                "  author varchar(255) NOT NULL," +
                "  genre varchar(255) NOT NULL," +
                "  quantity INT(100) NOT NULL," +
                "  price double NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);


        sql = "CREATE TABLE IF NOT EXISTS user (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  firstName varchar(255) NOT NULL," +
                "  lastName varchar(255) NOT NULL," +
                "  address varchar(255) NOT NULL," +
                "  email varchar(255) NOT NULL," +
                "  phone varchar(255) NOT NULL," +
                "  salary double NOT NULL," +
                "  username varchar(255) NOT NULL," +
                "  password varchar(255) NOT NULL," +
                "  role varchar(255) NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS shopping_basket_item (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  shopping_basket_id BIGINT(100) NOT NULL," +
                "  item_id BIGINT(100) NOT NULL," +
                "  quantity int(10) NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS shopping_basket (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  user_id BIGINT(100) NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }
}
