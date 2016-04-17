package com.phoneBook.dao.util;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DataConnection {
    private final String dbURL = "jdbc:mysql://localhost:3306/phoneBook"
            + "?verifyServerCertificate=false" //  bypassing the certificate validation
            + "&useSSL=false"
            + "&requireSSL=false";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbURL, "root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}