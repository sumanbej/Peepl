package com.smartcontact.Controllers;

import com.smartcontact.Entity.User;
import com.smartcontact.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User user= new User();
        user.setName("Test User");
        user.setEmail("abc@ymail.in");
        userRepository.save(user);
        return "working";
    }
}
