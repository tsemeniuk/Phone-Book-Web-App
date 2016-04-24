package com.phoneBook.dao;

import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class JsonContactDaoTest {
    private JsonContactDao jsonContactDao;

    @Before
    public void setup() {
        jsonContactDao = new JsonContactDao();
    }

    @Test
    public void testFindAllByUsername() throws Exception {
        //prepare
        User user = new User();
        user.setUsername("user");

        //then
        List<Contact> byUsername = jsonContactDao.findAllByUsername(user.getUsername());

        //verify
        assertNotNull(byUsername);
    }

    @Test
    public void testFindOne() throws Exception {
        //prepare
        Contact contact = new Contact();
        contact.setId(1);

        //then
        Contact actual = jsonContactDao.findOne(contact.getId());

        //verify
        assertNotNull(actual);
        assertEquals(contact.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void testDelete() throws Exception {
        //prepare
        Contact contact = mock(Contact.class);
        contact.setId(1);

        //when
        doThrow(new Exception()).when(jsonContactDao).delete(contact.getId());

        //then
        jsonContactDao.delete(contact.getId());

        //verify
        verify(jsonContactDao).delete(contact.getId());
        verify(jsonContactDao, times(1)).delete(contact.getId());
    }

    @Test(expected = Exception.class)
    public void testSave() throws Exception {
        //prepare
        Contact contact = mock(Contact.class);
        contact.setId(1);

        //when
        doThrow(new Exception()).when(jsonContactDao).save(contact);

        //then
        jsonContactDao.save(contact);

        //verify
        verify(jsonContactDao).save(contact);
        verify(jsonContactDao, times(1)).save(contact);
    }
}