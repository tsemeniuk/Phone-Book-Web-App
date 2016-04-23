package com.phoneBook.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.models.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class JsonUserDao {

    public User findByUsername(String username) {
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap userMap = root.get("user");

            for (Object o : userMap.values()) {
                if (o.toString().contains("=" + username + ",")) {

                    String editedJsonString = o.toString().replace("=", "\" : \"")
                            .replace("{", "{\"").replace("}", "\"}")
                            .replace(",", "\" ,\"").replace(" ", "");
                    return mapper.readValue(editedJsonString, User.class);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void save(User user) {
        try {
            String jsonFile = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(jsonFile, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap userMap = root.get("user");
            user.setId(userMap.size() + 1);
            userMap.put(userMap.size() + 1, user);

            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
