package com.phoneBook.controllers;

import com.phoneBook.entity.User;
import com.phoneBook.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class IndexController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public String welcome(Model model) throws SQLException, ClassNotFoundException {
        User user = userService.get(1);
        model.addAttribute("user", user);
        return "welcome";
    }

    /*@RequestMapping("/home")
    public String foo(Model model) throws SQLException, ClassNotFoundException {
        contactService.getAll(1);
        return "home";
    }*/

    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("Foo");
    }
}