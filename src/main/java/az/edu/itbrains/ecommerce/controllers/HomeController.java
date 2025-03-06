package az.edu.itbrains.ecommerce.controllers;


import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeFeaturedDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeRecentDto;
import az.edu.itbrains.ecommerce.dtos.slider.SliderHomeDto;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.ProductService;
import az.edu.itbrains.ecommerce.services.SliderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    private final CategoryService categoryService;
    private final ProductService productService;
    private final SliderService sliderService;


    public HomeController(CategoryService categoryService, ProductService productService, SliderService sliderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.sliderService = sliderService;
    }


    @GetMapping("/")
    public String index(Model model){

        List<CategoryHomeDto> categoryHomeDtoList = categoryService.getHomeCategories();
        List<ProductHomeFeaturedDto> productHomeFeaturedDtoList = productService.getHomeFeaturedProducts();
        List<ProductHomeRecentDto> productHomeRecentDtoList = productService.getHomeRecentProducts();
        List<SliderHomeDto> sliderHomeDtoList = sliderService.getHomeSlider();


        model.addAttribute("categories",categoryHomeDtoList);
        model.addAttribute("featuredProducts",productHomeFeaturedDtoList);
        model.addAttribute("recentProducts",productHomeRecentDtoList);
        model.addAttribute("sliders",sliderHomeDtoList);
        return "index.html";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }

}
