package com.phoneBook.service;

import com.phoneBook.models.User;

import java.sql.SQLException;

public interface UserService {
    public User get(int i) throws SQLException, ClassNotFoundException;

    public void add(User user);
}
