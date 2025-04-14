package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.repositories.CategoryRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryHomeDto> getHomeCategories() {

        List<Category> findCategories = categoryRepository.findTop12ByOrderByIdDesc();
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
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDashboardDto> categoryDashboardDtoList = categoryList.stream().map(category -> {
            CategoryDashboardDto categoryDashboardDto = modelMapper.map(category, CategoryDashboardDto.class);
            categoryDashboardDto.setProductCount(category.getProducts().size());
            return categoryDashboardDto;
        }).collect(Collectors.toList());
        return categoryDashboardDtoList;
    }

    @Override
    public CategoryUpdateDto getUpdatedCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(category,CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(id).orElseThrow();
        modelMapper.map(categoryUpdateDto,category);
        categoryRepository.save(category);
    }

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
