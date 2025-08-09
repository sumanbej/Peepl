package com.smartcontact.Controllers;

import com.smartcontact.DTO.RegistrationRequest;
import com.smartcontact.Entity.User;
import com.smartcontact.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

@Autowired
private UserRepository userRepository;


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
        model.addAttribute("user", new RegistrationRequest());
        return "register";}


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest request,Model model) {
        if (request.getPassword() == null ||
                !request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setAbout(request.getAbout());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getName());
        return "redirect:/login";
    }
}
