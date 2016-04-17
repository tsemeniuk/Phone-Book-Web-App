package com.phoneBook.dao;


import com.phoneBook.dao.util.DataConnection;
import com.phoneBook.dao.util.UserMapper;
import com.phoneBook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    private DataConnection dataConnection;
    private final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE id = ?";
    private final String ADD_NEW_USER = "INSERT INTO USER WHERE id = ?";

    public User get(int userId) throws SQLException, ClassNotFoundException {
        try (java.sql.Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {

            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return UserMapper.map(resultSet);
        }
    }

    public void add(User user) {
        try (java.sql.Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER);) {




        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
