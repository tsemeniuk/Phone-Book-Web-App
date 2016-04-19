package com.phoneBook.service;

import com.phoneBook.models.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactService {
    public List<Contact> getAll(int i) throws SQLException, ClassNotFoundException;

    public Contact get(int i);

    public void delete(int i);

    public void add(Contact contact, int userId);

    public void edit(Contact contact);
}
