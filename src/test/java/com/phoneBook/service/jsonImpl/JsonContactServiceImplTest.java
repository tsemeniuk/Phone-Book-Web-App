package com.phoneBook.service.jsonImpl;

import com.phoneBook.dao.JsonContactDao;
import com.phoneBook.models.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class JsonContactServiceImplTest {
    @Mock
    private JsonContactDao jsonContactDao;
    @InjectMocks
    private JsonContactServiceImpl jsonContactService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllByUsername() throws Exception {
        //prepare
        List<Contact> mockList = new ArrayList<>();
        mockList.add(new Contact());
        mockList.add(new Contact());
        mockList.add(new Contact());
        String username = "username";

        //prepare
        when(jsonContactDao.findAllByUsername(username)).thenReturn(mockList);

        //then
        List<Contact> contacts = jsonContactService.findAllByUsername(username);

        //verify
        verify(jsonContactDao, times(1));
        assertNotNull(contacts);
        assertEquals(3, contacts.size());

    }

    @Test
    public void testFindOne() throws Exception {
        //prepare
        Contact contact = mock(Contact.class);

        //when
        when(jsonContactDao.findOne(any(Integer.class))).thenReturn(contact);

        //then
        jsonContactService.findOne(any(Integer.class));

        //verify
        verify(jsonContactDao, times(1));
        assertNotNull(contact);
    }

    @Test
    public void testDelete() throws Exception {
        //when
        doNothing().when(jsonContactDao).delete(any(Integer.class));

        //then
        jsonContactService.delete(any(Integer.class));

        //verify
        verify(jsonContactDao, times(1)).delete(any(Integer.class));
    }

    @Test
    public void testSave() throws Exception {
        //when
        doNothing().when(jsonContactDao).save(any(Contact.class));

        //then
        jsonContactService.save(any(Contact.class));

        //verify
        verify(jsonContactDao, times(1));
    }
}