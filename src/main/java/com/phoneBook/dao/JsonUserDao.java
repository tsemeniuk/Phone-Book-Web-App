package com.phoneBook.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.dao.util.StringCorrector;
import com.phoneBook.dao.util.ValidateDb;
import com.phoneBook.models.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class JsonUserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUserDao.class);
    @Autowired
    private ValidateDb validateDb;
    @Autowired
    StringCorrector stringCorrector;

    public User findByUsername(String username) {
        validateDb.validate();
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap userMap = root.get("user");

            for (Object o : userMap.values()) {
                if (o.toString().contains("=" + username + ",")) {

                    String editedJsonString = stringCorrector.correctString(o);
                    return mapper.readValue(editedJsonString, User.class);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void save(User user) {
        validateDb.validate();
        try {
            String jsonFile = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(jsonFile, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap userMap = root.get("user");
            user.setId(userMap.size() + 1);
            userMap.put(userMap.size() + 1, user);

            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
            LOGGER.info("Пользователь сохранен в базе данных, успешно.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
