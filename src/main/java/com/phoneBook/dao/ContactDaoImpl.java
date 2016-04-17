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
    private final String ADD_NEW_CONTACT = "INSERT INTO contact WHERE id = ?)";
    private final String SELECT_CONTACT_BY_ID = "SELECT * FROM CONTACT WHERE id = ?";
    private final String DELETE_CONTACT_BY_ID = "DELETE * FROM CONTACT WHERE id = ?";

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

    public void add(Contact contact) {
        try (Connection connection = dataConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_CONTACT);) {
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Contact contact) {

    }
}
