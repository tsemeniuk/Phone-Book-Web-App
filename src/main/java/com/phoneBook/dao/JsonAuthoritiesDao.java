package com.phoneBook.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.models.Authorities;
import com.phoneBook.models.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class JsonAuthoritiesDao {

    public static void main(String[] args) {
        Authorities authorities = new Authorities();
        authorities.setUsername("TEST USER NAME");
        authorities.setAuthority("ROLE_USER");
        JsonAuthoritiesDao jsonAuthoritiesDao = new JsonAuthoritiesDao();
        jsonAuthoritiesDao.save(authorities);


    }
    public User findByUsername(String username) {
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap userMap = root.get("authorities");

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

    public void save(Authorities authorities) {
        try {
            String jsonFile = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(jsonFile, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap authorityMap = root.get("authority");
            authorities.setId(authorityMap.size() + 1);
            authorityMap.put(authorityMap.size() + 1, authorities);

            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
