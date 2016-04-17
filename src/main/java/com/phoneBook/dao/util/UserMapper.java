package com.phoneBook.dao.util;

import com.phoneBook.entity.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper {
    public static User map(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setSecondName(resultSet.getString("secondName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
