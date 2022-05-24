package com.learning.spring.dao;

import org.springframework.stereotype.Component;
import com.learning.spring.util.PropertiesDbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class JDBC {
    private static Connection connection;
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String DRIVER_KEY = "db.driver";
    private static JDBC instance;

    private JDBC() {

    }

    public static JDBC getInstance() {
        if (instance == null) {
            instance = new JDBC();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName(PropertiesDbUtil.get(DRIVER_KEY));
            connection = DriverManager.getConnection(
                    PropertiesDbUtil.get(URL_KEY),
                    PropertiesDbUtil.get(USERNAME_KEY),
                    PropertiesDbUtil.get(PASSWORD_KEY));


        } catch (SQLException |
                ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        return connection;
    }
}

