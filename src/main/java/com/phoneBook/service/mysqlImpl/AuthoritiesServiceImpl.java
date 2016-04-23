package com.phoneBook.service.mysqlImpl;

import com.phoneBook.models.Authorities;
import com.phoneBook.repository.AuthoritiesRepository;
import com.phoneBook.service.interfaces.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("mysql")
@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public void save(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }
}
