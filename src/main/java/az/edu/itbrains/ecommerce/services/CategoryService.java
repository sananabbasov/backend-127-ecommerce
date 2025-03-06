package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;

import java.util.List;

public interface CategoryService {
        List<CategoryHomeDto> getHomeCategories();
    }
