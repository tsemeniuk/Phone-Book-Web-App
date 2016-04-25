package com.phoneBook.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.dao.util.JsonDbTable;
import com.phoneBook.models.Authorities;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class JsonAuthoritiesDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthoritiesDao.class);

    public Authorities findByUsername(String username) {
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            HashMap<String,Authorities> authorityMap = root.getAuthority();

            for (Authorities authority : authorityMap.values()) {
                if (authority.getUsername().equals(username)) {
                    return authority;
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

            HashMap<String,Authorities> authorityMap = root.getAuthority();
            authorities.setId(authorityMap.size() + 1);
            authorityMap.put(String.valueOf(authorityMap.size() + 1), authorities);

            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
            LOGGER.info("Сахранения уровня доступа пользователя успешно.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
