package com.phoneBook.service.interfaces;

import com.phoneBook.models.Authorities;
import org.springframework.stereotype.Service;

@Service
public interface AuthoritiesService {
    public void save(Authorities authorities);

}
