package com.phoneBook.dao;

import com.phoneBook.dao.util.InitializeJsonDao;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class InitializeJsonDaoTest {
    private InitializeJsonDao initializeJsonDao;

    @Before
    public void setup() {
        initializeJsonDao = mock(InitializeJsonDao.class);
    }

    @Test(expected = Exception.class)
    public void testInitializeJsonDataBase() throws Exception {
        //when
        doThrow(new Exception()).when(initializeJsonDao).initializeJsonDataBase();

        //then
        initializeJsonDao.initializeJsonDataBase();

        //verify
        verify(initializeJsonDao).initializeJsonDataBase();
        verify(initializeJsonDao, times(1)).initializeJsonDataBase();
    }
}