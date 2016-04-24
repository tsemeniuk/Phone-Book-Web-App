package com.phoneBook.dao.util;

import com.phoneBook.dao.InitializeJsonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ValidateDb {
    @Autowired
    private InitializeJsonDao initializeJsonDao;

    public void validate() {
        File file = new File("jsonDataBase.json");
        if (!file.exists()) {
            initializeJsonDao.initializeJsonDataBase();
        }
    }
}
