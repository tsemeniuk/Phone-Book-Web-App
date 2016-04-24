package com.phoneBook.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoneBook.models.Contact;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class JsonContactDao {


    public static void main(String[] args) {
        JsonContactDao jsonContactDao = new JsonContactDao();
        jsonContactDao.findAllByUsername("user");
    }

    public List<Contact> findAllByUsername(String userName) {
        List list = new ArrayList();
        try {
            String str = FileUtils.readFileToString(new File("jsonDataBase.json"));
            ObjectMapper mapper = new ObjectMapper();
            JsonDbTable root = mapper.readValue(str, JsonDbTable.class);

            HashMap contactMap = root.get("contact");

            for (Object o : contactMap.values()) {
                if (o.toString().contains("=" + userName)) {

                    String editedJsonString = correctString(o);
                    System.out.println(editedJsonString);
                    Contact contact = mapper.readValue(editedJsonString, Contact.class);
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

            HashMap contactMap = root.get("contact");

            for (Object o : contactMap.values()) {
                if (o.toString().contains("=" + contactId + ",")) {

                    String editedJsonString = correctString(o);
                    return mapper.readValue(editedJsonString, Contact.class);
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

            HashMap contactMap = root.get("contact");

            Iterator it = contactMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getValue().toString().contains("=" + contactId + ",")) {
                    it.remove(); // avoids a ConcurrentModificationException
                    contactMap = rebuildMap(contactMap, mapper);
                    root.replace("contact", contactMap);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap rebuildMap(HashMap contactMap, ObjectMapper mapper) throws IOException {
        HashMap newMap = new HashMap();
        ArrayList list = new ArrayList(contactMap.values());

        for (int i = 0; i < list.size(); i++) {
            String jsonStringObject = list.get(i).toString();

            String editedJsonString = correctString(jsonStringObject);
            Contact contact = mapper.readValue(editedJsonString, Contact.class);
            contact.setId(i + 1);
            newMap.put(i + 1, contact);
        }
        return newMap;
    }

    public void save(Contact contact) {
        try {
            String jsonFile = FileUtils.readFileToString(new File("jsonDataBase.json"));
            JsonDbTable root = new ObjectMapper().readValue(jsonFile, JsonDbTable.class);
            ObjectMapper mapper = new ObjectMapper();

            HashMap contactMap = root.get("contact");


            if (contact.getId() == null) {
                contact.setId(contactMap.size() + 1);
                contactMap.put(contact.getId(), contact);

            } else {
                contactMap.put(contact.getId(), contact);
            }

            jsonFile = mapper.writeValueAsString(root);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("jsonDataBase.json"), root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String correctString(Object string) {
        return string.toString().replace("=", "\" : \"")
                .replace("{", "{\"").replace("}", "\"}")
                .replace(",", "\" ,\"").replace(" ", "");
    }
}
