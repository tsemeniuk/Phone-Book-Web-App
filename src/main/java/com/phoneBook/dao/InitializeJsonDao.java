package com.phoneBook.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@Service
public class InitializeJsonDao {
    public static void main(String[] args) throws IOException {
        InitializeJsonDao initializeJsonDao = new InitializeJsonDao();
        initializeJsonDao.initializeJsonDataBase();
    }

    @PostConstruct
    public void initializeJsonDataBase() throws IOException {
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();
        String str = FileUtils.readFileToString(new File("jsonDataBase.json"));

        JsonDbTable root = new JsonDbTable();
        root.put("user", new HashMap());
        root.put("contact", new HashMap());
        root.put("authority", new HashMap());

        for (int i = 1; i < 2; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("user");
            user.setPassword("pass");
            user.setFirstName("Юзер");
            user.setSecondName("Юзеров");
            user.setLastName("Юзерович");
            user.setEnabled(true);
            root.get("user").put(i, user);
        }
        for (int i = 1; i < 11; i++) {
            Contact contact = new Contact();
            contact.setId(i);
            contact.setFirstName("Юзер");
            contact.setSecondName("Юзерович");
            contact.setLastName("Юзеров");
            contact.setPhoneMobile("(067) 171-" + (random.nextInt(89) + 10) + "-" + (random.nextInt(89) + 10));
            contact.setPhoneHome("(093) 371-" + (random.nextInt(89) + 10) + "-" + (random.nextInt(89) + 10));
            contact.setAddress("UA");
            contact.setEmail("some@email.com");
            contact.setUsername("user");
            root.get("contact").put(i, contact);
        }
        for (int i = 1; i < 2; i++) {
            Authorities authority = new Authorities();
            authority.setId(i);
            authority.setUsername("user");
            authority.setAuthority("ROLE_USER");
            root.get("authority").put(i, authority);
        }

        str = mapper.writeValueAsString(root);
        System.out.println(str);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
    }
}
