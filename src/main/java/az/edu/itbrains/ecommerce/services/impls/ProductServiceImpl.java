package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.product.*;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.PhotoService;
import az.edu.itbrains.ecommerce.services.ProductService;
import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final PhotoService photoService;
    private final CategoryService categoryService;


    @Override
    public List<ProductHomeFeaturedDto> getHomeFeaturedProducts() {
        List<Product> findProducts = productRepository.findByFeaturedTrue();
        List<ProductHomeFeaturedDto> products = findProducts.stream().map(product -> modelMapper.map(product, ProductHomeFeaturedDto.class)).collect(Collectors.toList());
        products.forEach(c-> {
            String photoUrl = photoService.findPhotoByProductId(c.getId());
            c.setPhotoUrl(photoUrl);
        });
        return products;
    }

    @Override
    public ProductDetailDto getDetailProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);
        return productDetailDto;
    }

    @Override
    public List<ProductHomeRecentDto> getHomeRecentProducts() {

        List<Product> findProducts = productRepository.findAll();
        List<ProductHomeRecentDto> products = findProducts.stream().map(product -> modelMapper.map(product, ProductHomeRecentDto.class)).collect(Collectors.toList());
        products.forEach(c-> {
            String photoUrl = photoService.findPhotoByProductId(c.getId());
            c.setPhotoUrl(photoUrl);
        });
        return products;
    }

    @Override
    public List<ProductRelatedDto> getRelatedProducts(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        Long categoryId = product.getCategory().getId();
        List<Product> productList = productRepository.findTop5ByCategoryIdOrderByIdDesc(categoryId);
        List<ProductRelatedDto> productRelatedDtoList = productList.stream().map(pro -> modelMapper.map(pro, ProductRelatedDto.class)).collect(Collectors.toList());
        productRelatedDtoList.forEach(c-> {
            String photoUrl = photoService.findPhotoByProductId(c.getId());
            c.setPhotoUrl(photoUrl);
        });
        return productRelatedDtoList;
    }

    @Override
    public List<ProductDashboardDto> getDashboardCategories() {
        List<Product> productList = productRepository.findAll();
        List<ProductDashboardDto> productDashboardDtoList = productList.stream().map(product -> modelMapper.map(product, ProductDashboardDto.class)).collect(Collectors.toList());
        return productDashboardDtoList;
    }

    @Override
    public void createProduct(ProductCreateDto productCreateDto) {

        try{
            Product product = modelMapper.map(productCreateDto, Product.class);
            Category category = categoryService.getCategoryById(productCreateDto.getCategoryId());
            product.setCategory(category);
            productRepository.save(product);
            photoService.uploads(productCreateDto.getFiles(),product);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ProductUpdateDto getUpdatedProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductUpdateDto updateDto = modelMapper.map(product, ProductUpdateDto.class);
        updateDto.setCategoryId(product.getCategory().getId());
        return updateDto;
    }

    @Override
    public void updateProduct(Long id, ProductUpdateDto productUpdateDto) {

    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
