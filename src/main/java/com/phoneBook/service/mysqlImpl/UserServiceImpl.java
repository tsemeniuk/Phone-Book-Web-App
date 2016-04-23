package com.phoneBook.service.mysqlImpl;

import com.phoneBook.models.User;
import com.phoneBook.repository.UserRepository;
import com.phoneBook.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("mysql")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
