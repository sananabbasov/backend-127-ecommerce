package az.edu.itbrains.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OrderController {

    @PostMapping("/order")
    public String order(){
        return "index.html";
    }
}
