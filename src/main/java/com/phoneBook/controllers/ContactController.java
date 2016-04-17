package com.phoneBook.controllers;

import com.phoneBook.entity.Contact;
import com.phoneBook.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class ContactController {
    @Autowired
    private ContactServiceImpl contactService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("firstName") String firstName,
                      @RequestParam("secondName") String secondName,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("phoneMobile") String phoneMobile,
                      @RequestParam("phoneHome") String phoneHome,
                      @RequestParam("address") String address,
                      @RequestParam("email") String email,
                      Model model
    ) throws SQLException, ClassNotFoundException {

        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setSecondName(secondName);
        contact.setLastName(lastName);
        contact.setPhoneMobile(phoneMobile);
        contact.setPhoneHome(phoneHome);
        contact.setAddress(address);
        contact.setEmail(email);
        contactService.add(contact);

        System.out.println("ADD CONTACT ");
        System.out.println(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam("id") int id,
                       @RequestParam("firstName") String firstName,
                       @RequestParam("secondName") String secondName,
                       @RequestParam("lastName") String lastName,
                       @RequestParam("phoneMobile") String phoneMobile,
                       @RequestParam("phoneHome") String phoneHome,
                       @RequestParam("address") String address,
                       @RequestParam("email") String email) {
        Contact contact = contactService.get(id);
        contact.setFirstName(firstName);
        contact.setSecondName(secondName);
        contact.setLastName(lastName);
        contact.setPhoneMobile(phoneMobile);
        contact.setPhoneHome(phoneHome);
        contact.setAddress(address);
        contact.setEmail(email);
        contactService.edit(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") int id) {
        contactService.delete(id);
        return "redirect:/";
    }
}
