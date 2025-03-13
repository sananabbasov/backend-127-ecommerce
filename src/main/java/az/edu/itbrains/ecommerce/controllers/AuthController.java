package az.edu.itbrains.ecommerce.controllers;


import az.edu.itbrains.ecommerce.dtos.auth.RegisterDto;
import az.edu.itbrains.ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/auth/login.html";
    }



    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid  RegisterDto registerDto, BindingResult result){
        if (result.hasErrors()){
            return "/auth/register.html";
        }

        userService.registerUser(registerDto);
        return "redirect:/login";
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
