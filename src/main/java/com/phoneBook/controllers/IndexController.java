package com.phoneBook.controllers;

import com.phoneBook.entity.User;
import com.phoneBook.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public String welcome(Model model)  {
        User user = userService.get(1);
        model.addAttribute("user", user);
        return "welcome";
    }

    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("Foo");
    }
}