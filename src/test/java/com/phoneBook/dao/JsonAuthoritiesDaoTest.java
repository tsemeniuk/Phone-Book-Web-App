package com.phoneBook.dao;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class JsonAuthoritiesDaoTest {
    private JsonAuthoritiesDao jsonAuthoritiesDao;

    @Before
    public void setup() {
        jsonAuthoritiesDao = new JsonAuthoritiesDao();
    }

    @Test
    public void testFindByUsername() throws Exception {
        //prepare
        User user = new User();
        user.setUsername("user");

        //then
        Authorities actual = jsonAuthoritiesDao.findByUsername(user.getUsername());

        //verify
        assertNotNull(actual);
        assertEquals(user.getUsername(),actual.getUsername());
    }

    @Test(expected = Exception.class)
    public void testSave() throws Exception {
        //prepare
        Authorities authorities = mock(Authorities.class);
        authorities.setId(1);

        //when
        doThrow(new Exception()).when(jsonAuthoritiesDao).save(authorities);

        //then
        jsonAuthoritiesDao.save(authorities);
        //verify
        verify(jsonAuthoritiesDao).save(authorities);
        verify(jsonAuthoritiesDao, times(1)).save(authorities);
    }
}