package com.smartcontact.Controllers;

import com.smartcontact.Entity.User;
import com.smartcontact.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("title", "Home - Peepl");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Peepl");
        return "about";}
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact - Peepl");
        return "contact";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login - Peepl");
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register - Peepl");
        model.addAttribute("user", new User());
        return "register";}
}
