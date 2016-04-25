package com.phoneBook.service.jsonImpl;

import com.phoneBook.dao.JsonAuthoritiesDao;
import com.phoneBook.models.Authorities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


public class JsonAuthoritiesServiceImplTest {
    @Mock
    private JsonAuthoritiesDao jsonAuthoritiesDao;
    @InjectMocks
    private JsonAuthoritiesServiceImpl jsonAuthoritiesService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = Exception.class)
    public void testSave() throws Exception {
        //prepare
        Authorities authority = new Authorities();

        //when
        doThrow(new Exception()).when(jsonAuthoritiesDao).save(authority);

        //then
        jsonAuthoritiesService.save(authority);

        //verify
        verify(jsonAuthoritiesDao, times(1)).save(authority);
    }
}