package com.phoneBook.service.jsonImpl;

import com.phoneBook.dao.JsonAuthoritiesDao;
import com.phoneBook.models.Authorities;
import com.phoneBook.service.interfaces.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("json")
@Service
public class JsonAuthoritiesServiceImpl implements AuthoritiesService {
    @Autowired
    private JsonAuthoritiesDao jsonAuthoritiesDao;

    @Override
    public void save(Authorities authorities) {
        jsonAuthoritiesDao.save(authorities);
    }
}
