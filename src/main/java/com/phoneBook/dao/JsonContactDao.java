package com.phoneBook.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.dao.util.JsonDbTable;
import com.phoneBook.models.Contact;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class JsonContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonContactDao.class);

    public List<Contact> findAllByUsername(String userName) {
        List<Contact> list = new ArrayList<>();
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            ObjectMapper mapper = new ObjectMapper();
            JsonDbTable root = mapper.readValue(str, JsonDbTable.class);

            HashMap<String,Contact> contactMap = root.getContact();

            for (Contact contact : contactMap.values()) {
                if (contact.getUsername().equals(userName)) {
                    list.add(contact);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(list);
        return list;
    }
    public Contact findOne(int contactId) {
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap<String,Contact> contactMap = root.getContact();

            for (Contact contact : contactMap.values()) {
                if (contact.getId().equals(contactId)) {
                    return contact;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void delete(int contactId) {
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(str, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,Contact> contactMap = root.getContact();

            Iterator it = contactMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Contact contact = (Contact) pair.getValue();
                if (contact.getId().equals(contactId)) {

                    it.remove(); // avoids a ConcurrentModificationException
                    contactMap = rebuildMap(contactMap);
                    root.setContact(contactMap);

                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
                    LOGGER.info("Контакт был удален успешно.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String,Contact> rebuildMap(HashMap<String,Contact> contactMap) throws IOException {
        HashMap<String,Contact> newMap = new HashMap<>();
        ArrayList<Contact> list = new ArrayList<>(contactMap.values());

        for (int i = 0; i < list.size(); i++) {
            Contact contact = list.get(i);
            contact.setId(i + 1);
            newMap.put(String.valueOf(i + 1), contact);
        }
        return newMap;
    }

    public void save(Contact contact) {
        try {
            String jsonFile = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(jsonFile, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,Contact> contactMap = root.getContact();

            if (contact.getId() == null) {
                contact.setId(contactMap.size() + 1);
                contactMap.put(String.valueOf(contact.getId()), contact);

            } else {
                contactMap.put(String.valueOf(contact.getId()), contact);
            }
            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
            LOGGER.info("Контакт был добавлен, в базу данных, успешно.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
