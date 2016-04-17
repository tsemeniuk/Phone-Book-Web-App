package com.phoneBook.service;


import com.phoneBook.dao.ContactDaoImpl;
import com.phoneBook.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDaoImpl contactDao;

//    @Autowired
//    private ContactRepository contactRepository;

    public List<Contact> getAll(int userId) {
        return contactDao.getAll(userId);
    }

    public Contact get(int i) {
        return contactDao.get(i);
    }

    public void delete(int userId) {
        contactDao.delete(userId);
    }

    public void add(Contact contact, int userId) {
        contactDao.add(contact, userId);
    }

    public void edit(Contact contact) {
        contactDao.edit(contact);
    }
}
