package com.phoneBook.service.mysqlImpl;


import com.phoneBook.models.Contact;
import com.phoneBook.repository.ContactRepository;
import com.phoneBook.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("mysql")
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAllByUsername(String userName) {
        return contactRepository.findAllByUsername(userName);
    }

    @Override
    public Contact findOne(int contactId) {
        return contactRepository.findOne(contactId);
    }

    @Override
    public void delete(int contactId) {
        contactRepository.delete(contactId);
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }
}
