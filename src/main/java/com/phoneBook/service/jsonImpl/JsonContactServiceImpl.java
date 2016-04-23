package com.phoneBook.service.jsonImpl;


import com.phoneBook.dao.JsonContactDao;
import com.phoneBook.models.Contact;
import com.phoneBook.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("json")
@Service
public class JsonContactServiceImpl implements ContactService {
    @Autowired
    private JsonContactDao jsonContactDao;

    @Override
    public List<Contact> findAllByUsername(String userName) {
        return jsonContactDao.findAllByUsername(userName);
    }

    @Override
    public Contact findOne(int contactId) {
        return jsonContactDao.findOne(contactId);
    }

    @Override
    public void delete(int contactId) {
        jsonContactDao.delete(contactId);
    }

    @Override
    public void save(Contact contact) {
        jsonContactDao.save(contact);
    }
}
