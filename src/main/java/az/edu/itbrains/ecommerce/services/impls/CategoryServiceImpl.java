package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.repositories.CategoryRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryHomeDto> getHomeCategories() {

        List<Category> findCategories = categoryRepository.findAll().stream().limit(12).collect(Collectors.toList());
        List<CategoryHomeDto> categories = new ArrayList<>();
        for (Category category : findCategories) {
            CategoryHomeDto categoryHomeDto = new CategoryHomeDto();

            categoryHomeDto.setId(category.getId());
            categoryHomeDto.setName(category.getName());
            categoryHomeDto.setPhotoUrl(category.getPhotoUrl());

            categories.add(categoryHomeDto);
        }

        return categories;
    }

    @Override
    public List<CategoryDashboardDto> getDashboardCategories() {
        return null;
    }

    @Override
    public CategoryUpdateDto getUpdatedCategory(Long id) {
        return null;
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {

    }
}
