package com.phoneBook.repository;

import com.phoneBook.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    public List<Contact> findAllByUsername(String username);
}
