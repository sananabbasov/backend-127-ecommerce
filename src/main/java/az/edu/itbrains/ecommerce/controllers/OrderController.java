package az.edu.itbrains.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {

    @GetMapping("/order/user")
    public String test(){
        return "order.html";
    }
}
