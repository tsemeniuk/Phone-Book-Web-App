package com.phoneBook.service.interfaces;

import com.phoneBook.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void save(User user);

    public User findByUsername(String userName);
}
