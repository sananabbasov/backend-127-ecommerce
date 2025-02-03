package az.edu.itbrains.ecommerce.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {



    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id){
        return "detail.html";
    }
}
