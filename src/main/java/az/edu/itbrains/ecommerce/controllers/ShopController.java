package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.basket.BasketAddDto;
import az.edu.itbrains.ecommerce.dtos.basket.BasketDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.services.BasketService;
import az.edu.itbrains.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final BasketService basketService;

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


    @PostMapping("/cart/{id}")
    public String addToCart(Principal principal, BasketAddDto basketAddDto, @PathVariable Long id){
        if (principal == null){
            return "redirect:/login";
        }
        basketAddDto.setProductId(id);
        boolean result = basketService.addToCart(principal.getName(), basketAddDto);

        if (result == true){
            return "redirect:/cart";
        }
        return "redirect:/detail/"+basketAddDto.getProductId();
    }

    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String cart(Principal principal, Model model){
        List<BasketDto> basketDtoList = basketService.getUserBaskets();
        model.addAttribute("baskets", basketDtoList);
        return "cart.html";
    }


    @GetMapping("/checkout")
    public String checkout(){
        return "checkout.html";
    }
}
