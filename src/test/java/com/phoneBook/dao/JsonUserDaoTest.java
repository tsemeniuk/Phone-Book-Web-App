package com.phoneBook.dao;

import com.phoneBook.dao.util.ValidateDb;
import com.phoneBook.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class JsonUserDaoTest {
    @Mock
    private ValidateDb validateDb;
    @InjectMocks
    JsonUserDao jsonUserDao;

    @Before
    public void setup() {
        jsonUserDao = new JsonUserDao();
    }

    @Test(expected = Exception.class)
    public void testFindByUsername() throws Exception {
        //prepare
        User user = new User();
        user.setUsername("user");

        //when
        doThrow(new Exception()).when(validateDb).validate();

        //then
        User actual = jsonUserDao.findByUsername(user.getUsername());

        //verify
        assertNotNull(actual);
        assertEquals(user.getUsername(), actual.getUsername());
    }

    @Test(expected = Exception.class)
    public void testSave() throws Exception {
        //prepare
        User user = mock(User.class);
        user.setUsername("user");

        //when
        doThrow(new Exception()).when(jsonUserDao).findByUsername(user.getUsername());

        //then
        jsonUserDao.save(user);

        //verify
        verify(jsonUserDao).save(user);
        verify(jsonUserDao, times(1)).save(user);
    }
}