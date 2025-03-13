package az.edu.itbrains.ecommerce.controllers.dashboard;

import az.edu.itbrains.ecommerce.dtos.auth.RegisterDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.services.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/category")
    public String index(Model model){
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryService.getDashboardCategories();
        model.addAttribute("categories", categoryDashboardDtoList);
        return "/dashboard/category/index.html";
    }


    @GetMapping("/category/create")
    public String create(Model model){
        model.addAttribute("registerDto", new CategoryCreateDto());
        return "/dashboard/category/create.html";
    }


    @PostMapping("/category/create")
    public String create(@Valid CategoryCreateDto categoryCreateDto, BindingResult result){
        if (result.hasErrors()){
            return "/dashboard/category/create.html";
        }
        return "redirect:/category";
    }

    @GetMapping("/category/edit/{id}")
    public String update(@PathVariable Long id, Model model){
        CategoryUpdateDto categoryUpdateDto = categoryService.getUpdatedCategory(id);
        model.addAttribute("category", categoryUpdateDto);
        return "/dashboard/category/update.html";
    }


    @PostMapping("/category/edit/{id}")
    public String update(@PathVariable Long id, @Valid CategoryUpdateDto categoryUpdateDto, BindingResult result){
        if (result.hasErrors()){
            return "/dashboard/category/update.html";
        }
        categoryService.updateCategory(id, categoryUpdateDto);
        return "redirect:/category";
    }
}
