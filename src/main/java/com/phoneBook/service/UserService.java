package com.phoneBook.service;

import com.phoneBook.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void save(User user);

    public User findByUsername(String userName);
}
