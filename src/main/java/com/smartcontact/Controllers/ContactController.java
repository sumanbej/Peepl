package com.smartcontact.Controllers;

import com.smartcontact.Entity.Contact;
import com.smartcontact.Entity.User;
import com.smartcontact.Repository.ContactRepository;
import com.smartcontact.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/contact/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("title", "Add Contact - Peepl");
        model.addAttribute("contact", new Contact());
        return "ContactAdd";
    }

    @PostMapping("/contact/add")
    public String addContact(@ModelAttribute("contact") Contact contact, Principal principal) {
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail);
        contact.setUser(user);
        contactRepository.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/contacts")
    public String viewContacts(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail);
        List<Contact> contacts = contactRepository.findAll()
                .stream()
                .filter(c -> c.getUser() != null && c.getUser().getId() == user.getId())
                .toList();
        model.addAttribute("contacts", contacts);
        model.addAttribute("title", "Your Contacts - Peepl");
        return "contacts";
    }
}
