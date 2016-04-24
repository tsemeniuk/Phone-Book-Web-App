package com.phoneBook.dao.util;

import org.springframework.stereotype.Component;

@Component
public class StringCorrector {
    public String correctString(Object string) {
        return string.toString().replace("=", "\" : \"")
                .replace("{", "{\"").replace("}", "\"}")
                .replace(",", "\" ,\"").replace(" ", "");
    }
}
