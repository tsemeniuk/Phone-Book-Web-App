package com.phoneBook.controllers;

import com.phoneBook.entity.User;
import com.phoneBook.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/addUser")
    public String welcome(@ModelAttribute("User") User user/*,
                          @RequestParam("firstName") String firstName,
                          @RequestParam("secondName") String secondName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("login") String login,
                          @RequestParam("password") String password*/) {

        /*User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);*/
        userService.add(user);

        return "welcome";
    }

}