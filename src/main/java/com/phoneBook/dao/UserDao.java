package com.phoneBook.dao;


import com.phoneBook.models.User;

import java.sql.SQLException;

public interface UserDao {
    public User get(int i) throws SQLException, ClassNotFoundException;

    public void add(User user);
}
