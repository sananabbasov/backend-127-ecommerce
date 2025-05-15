package az.edu.itbrains.ecommerce.services;


import az.edu.itbrains.ecommerce.dtos.product.*;
import az.edu.itbrains.ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductHomeFeaturedDto> getHomeFeaturedProducts();
    ProductDetailDto getDetailProductById(Long id);
    List<ProductHomeRecentDto>  getHomeRecentProducts();
    List<ProductRelatedDto> getRelatedProducts(Long id);

    List<ProductDashboardDto> getDashboardCategories();

    void createProduct(ProductCreateDto productCreateDto);

    ProductUpdateDto getUpdatedProduct(Long id);

    void updateProduct(Long id, ProductUpdateDto productUpdateDto);

    Product findProductById(Long productId);
}
