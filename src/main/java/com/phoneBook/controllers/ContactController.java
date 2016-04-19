package com.phoneBook.controllers;

import com.phoneBook.models.Contact;
import com.phoneBook.repository.ContactRepository;
import com.phoneBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ContactController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("contact") Contact contact) {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contact") Contact contact, Principal principal) {
        contact.setUsername(principal.getName());
        contactRepository.save(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", contactRepository.findOne(id));
        return "edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("contact") Contact contact, @PathVariable int id, Principal principal) {
        contact.setUsername(principal.getName());
        contactRepository.save(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        contactRepository.delete(id);
        return "redirect:/";
    }
}
