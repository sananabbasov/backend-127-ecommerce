package az.edu.itbrains.ecommerce.controllers.dashboard;


import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDashboardDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/dashboard/product")
    public String index(Model model){
        List<ProductDashboardDto> productDashboardDtoList = productService.getDashboardCategories();
        model.addAttribute("products", productDashboardDtoList);
        return "/dashboard/product/index.html";
    }


    @GetMapping("/dashboard/product/create")
    public String create(Model model){

        List<CategoryDashboardDto> categoryDtoList = categoryService.getDashboardCategories();

        model.addAttribute("productCreateDto", new ProductCreateDto());
        model.addAttribute("categories", categoryDtoList);
        return "/dashboard/product/create.html";
    }


    @PostMapping("/dashboard/product/create")
    public String create(@Valid ProductCreateDto productCreateDto, BindingResult result, Model model){
        List<CategoryDashboardDto> categoryDtoList = categoryService.getDashboardCategories();
        if (result.hasErrors()){
            model.addAttribute("categories", categoryDtoList);

            return "/dashboard/product/create.html";

        }
        productService.createProduct(productCreateDto);
        return "redirect:/dashboard/product";
    }

    @GetMapping("/dashboard/product/edit/{id}")
    public String update(@PathVariable Long id, Model model){
        ProductUpdateDto productUpdateDto = productService.getUpdatedProduct(id);
        model.addAttribute("productUpdateDto", productUpdateDto);
        return "/dashboard/product/update.html";
    }


    @PostMapping("/dashboard/product/edit/{id}")
    public String update(@PathVariable Long id, @Valid ProductUpdateDto productUpdateDto, BindingResult result){
        if (result.hasErrors()){
            return "/dashboard/product/update.html";
        }
        productService.updateProduct(id, productUpdateDto);
        return "redirect:/dashboard/product";
    }
}
