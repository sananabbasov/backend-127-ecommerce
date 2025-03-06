package az.edu.itbrains.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {

    @GetMapping("/order")
    public String order(){
        return "index.html";
    }
}
