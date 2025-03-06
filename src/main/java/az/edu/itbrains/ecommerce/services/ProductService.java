package az.edu.itbrains.ecommerce.services;


import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeFeaturedDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeRecentDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;

import java.util.List;

public interface ProductService {
    List<ProductHomeFeaturedDto> getHomeFeaturedProducts();
    ProductDetailDto getDetailProductById(Long id);
    List<ProductHomeRecentDto>  getHomeRecentProducts();
    List<ProductRelatedDto> getRelatedProducts(Long id);
}
