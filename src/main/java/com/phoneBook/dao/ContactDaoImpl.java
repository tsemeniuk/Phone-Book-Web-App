package com.phoneBook.dao;

import com.phoneBook.dao.util.ContactMapper;
import com.phoneBook.dao.util.DataConnection;
import com.phoneBook.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private DataConnection dataConnection;
    private final String GET_USER_CONTACTS = "SELECT * FROM contact WHERE id IN (SELECT id FROM contact WHERE contact.user_id=?)";
    private final String ADD_NEW_CONTACT = "INSERT INTO contact(firstName, secondName, lastName, phoneMobile, phoneHome, " +
            "address, email, user_id) VALUES (?,?,?,?,?,?,?,?)";
    private final String SELECT_CONTACT_BY_ID = "SELECT * FROM CONTACT WHERE id = ?";
    private final String DELETE_CONTACT_BY_ID = "DELETE FROM CONTACT WHERE id = ?";
    private final String UPDATE_CONTACT_BY_ID = "UPDATE contact SET firstName = ?, secondName = ?, lastName = ?, phoneMobile = ?," +
            " phoneHome = ?, address = ?, email = ? WHERE id =";
//    UPDATE userdb SET fname = ?, mail = ? WHERE username = ?;


    public List<Contact> getAll(int userId) {
        List<Contact> contactList = new ArrayList<Contact>();

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_CONTACTS);) {

            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                contactList.add(ContactMapper.map(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    public Contact get(int contactId) {
        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTACT_BY_ID);) {
            preparedStatement.setInt(1, contactId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return ContactMapper.map(resultSet);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int contactId) {
        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT_BY_ID);) {
            preparedStatement.setInt(1, contactId);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Contact contact, int userId) {
        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_CONTACT);) {
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getSecondName());
            preparedStatement.setString(3, contact.getLastName());
            preparedStatement.setString(4, contact.getPhoneMobile());
            preparedStatement.setString(5, contact.getPhoneHome());
            preparedStatement.setString(6, contact.getAddress());
            preparedStatement.setString(7, contact.getEmail());
            preparedStatement.setInt(8, userId);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Contact contact) {
        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT_BY_ID + contact.getId());) {
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getSecondName());
            preparedStatement.setString(3, contact.getLastName());
            preparedStatement.setString(4, contact.getPhoneMobile());
            preparedStatement.setString(5, contact.getPhoneHome());
            preparedStatement.setString(6, contact.getAddress());
            preparedStatement.setString(7, contact.getEmail());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
