package com.smartcontact.Controllers;

import com.smartcontact.DTO.LoginRequest;
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
        return "register";
    }

    //error handling
    @GetMapping("/error")
    public String errorHandler() {
        return "error";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest request,Model model) {
        if (request.getPassword() == null ||
                !request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }
        // Mobile length validation
        if (request.getMobile() == null || request.getMobile().length() != 10) {
            model.addAttribute("error", "Mobile number must be exactly 10 digits.");
            return "register";
        }

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            model.addAttribute("error", "This email already exists. Please login or use another email.");
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
    //Dashboard mapping
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard - Peepl");
        return "dashboard";
    }

    //login post mapping
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") LoginRequest request, Model model) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }

        return "redirect:/home";
    }
}
