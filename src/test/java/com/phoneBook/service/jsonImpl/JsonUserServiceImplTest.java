package com.phoneBook.service.jsonImpl;

import com.phoneBook.dao.JsonUserDao;
import com.phoneBook.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class JsonUserServiceImplTest {
    @Mock
    private JsonUserDao jsonUserDao;
    @InjectMocks
    private JsonUserServiceImpl jsonUserService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        //prepare
        User user = new User();

        //when
        doNothing().when(jsonUserDao).save(user);

        //then
        jsonUserService.save(user);

        //verify
        verify(jsonUserDao, times(1)).save(user);
    }

    @Test
    public void testFindByUsername() throws Exception {
        //prepare
        User mockUser = mock(User.class);

        //when
        when(jsonUserDao.findByUsername(mockUser.getUsername())).thenReturn(mockUser);

        //then
        User resultUser = spy(jsonUserService).findByUsername(mockUser.getUsername());

        assertSame(resultUser, mockUser);
        assertNotNull(mockUser);

        //verify
        verify(jsonUserDao,times(1)).findByUsername(mockUser.getUsername());
    }
}