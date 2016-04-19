package com.phoneBook.dao.util;

import com.phoneBook.models.Contact;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactMapper {
    public static Contact map(ResultSet resultSet) {
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getInt("id"));
            contact.setFirstName(resultSet.getString("firstName"));
            contact.setSecondName(resultSet.getString("secondName"));
            contact.setLastName(resultSet.getString("lastName"));
            contact.setPhoneMobile(resultSet.getString("phoneMobile"));
            contact.setPhoneHome(resultSet.getString("phoneHome"));
            contact.setAddress(resultSet.getString("address"));
            contact.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }
}
