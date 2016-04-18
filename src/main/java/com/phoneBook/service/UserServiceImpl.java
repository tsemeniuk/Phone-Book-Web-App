package com.phoneBook.service;

import com.phoneBook.dao.ContactDaoImpl;
import com.phoneBook.dao.UserDaoImpl;
import com.phoneBook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private ContactDaoImpl contactDao;



    public User get(int i){
        User user = userDao.get(i);
        user.setContacts(contactDao.getAll(i));
        return user;
    }

    public void add(User user) {
        userDao.add(user);
    }
}
