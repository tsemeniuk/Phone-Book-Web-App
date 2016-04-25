package com.phoneBook.dao.util;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JsonDbTable {
    private HashMap<String, User> user;
    private HashMap<String, Contact> contact;
    private HashMap<String, Authorities> authority;

    public HashMap<String, User> getUser() {
        return user;
    }

    public void setUser(HashMap<String, User> user) {
        this.user = user;
    }

    public HashMap<String, Contact> getContact() {
        return contact;
    }

    public void setContact(HashMap<String, Contact> contact) {
        this.contact = contact;
    }

    public HashMap<String, Authorities> getAuthority() {
        return authority;
    }

    public void setAuthority(HashMap<String, Authorities> authority) {
        this.authority = authority;
    }
}
