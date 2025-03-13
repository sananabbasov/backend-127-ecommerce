package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeDto> getHomeCategories();
    List<CategoryDashboardDto> getDashboardCategories();
    CategoryUpdateDto getUpdatedCategory(Long id);
    void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);
}
