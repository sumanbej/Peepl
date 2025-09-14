package com.smartcontact.Controllers;

import com.smartcontact.Entity.Contact;
import com.smartcontact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("title", "Add Contact - Peepl");
        model.addAttribute("contact", new Contact());
        return "ContactAdd";
    }

    @PostMapping("/contact/add")
    public String addContact(@ModelAttribute("contact") Contact contact, Model model) {
        contactRepository.save(contact);
        model.addAttribute("message", "Contact added successfully!");
        return "redirect:/dashboard";
    }
}