package com.phoneBook.service.jsonImpl;

import com.phoneBook.dao.JsonUserDao;
import com.phoneBook.models.User;
import com.phoneBook.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("json")
@Service
public class JsonUserServiceImpl implements UserService {
    @Autowired
    private JsonUserDao jsonUserDao;

    @Override
    public void save(User user) {
        jsonUserDao.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return jsonUserDao.findByUsername(userName);
    }
}
