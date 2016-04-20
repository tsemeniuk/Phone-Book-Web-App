package com.phoneBook.controllers;

import com.phoneBook.models.Contact;
import com.phoneBook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("contact") Contact contact) {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contact") @Valid Contact contact,
                      BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "add";
        } else {
            contact.setUsername(principal.getName());
            contactRepository.save(contact);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", contactRepository.findOne(id));
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("contact") @Valid Contact contact, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            contact.setUsername(principal.getName());
            contactRepository.save(contact);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        contactRepository.delete(id);
        return "redirect:/";
    }
}
