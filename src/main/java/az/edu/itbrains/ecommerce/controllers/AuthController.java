package az.edu.itbrains.ecommerce.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/login")
    public String login(){
        return "/auth/login.html";
    }



    @GetMapping("/register")
    public String register(){
        return "/auth/register.html";
    }


    @GetMapping("/forgot-password")
    public String forgot(){
        return "/auth/forgot.html";
    }


    @GetMapping("/change-password")
    public String change(){
        return "/auth/change.html";
    }
}
