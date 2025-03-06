package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/shop")
    public String shop(){
        return "shop.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        ProductDetailDto productDetailDto = productService.getDetailProductById(id);
        List<ProductRelatedDto> productRelatedDtoList = productService.getRelatedProducts(id);

        model.addAttribute("product", productDetailDto);
        model.addAttribute("products", productRelatedDtoList);
        return "detail.html";
    }


    @GetMapping("/cart")
    public String cart(){
        return "cart.html";
    }


    @GetMapping("/checkout")
    public String checkout(){
        return "checkout.html";
    }
}
