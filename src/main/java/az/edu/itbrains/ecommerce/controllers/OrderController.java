package az.edu.itbrains.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class OrderController {

    @PostMapping("/order")
    public String order(){
        return "index.html";
    }
}
