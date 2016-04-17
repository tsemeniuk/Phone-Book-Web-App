package com.phoneBook.controllers;

import com.phoneBook.entity.Contact;
import com.phoneBook.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {
    @Autowired
    private ContactServiceImpl contactService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("contact") Contact contact) {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contact") Contact contact) {
//        @RequestParam("userId") int userId
        int userId = 1;
        contactService.add(contact, userId);

        System.out.println("ADD CONTACT ");
        System.out.println(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", contactService.get(id));
        return "edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("contact") Contact contact, @PathVariable int id) {
        contact.setId(id);
        contactService.edit(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        contactService.delete(id);
        return "redirect:/";
    }
}
