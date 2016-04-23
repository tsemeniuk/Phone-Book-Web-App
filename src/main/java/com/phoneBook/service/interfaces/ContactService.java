package com.phoneBook.service.interfaces;

import com.phoneBook.models.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    public List<Contact> findAllByUsername(String userName);

    public Contact findOne(int contactId);

    public void delete(int i);

    public void save(Contact contact);

}
