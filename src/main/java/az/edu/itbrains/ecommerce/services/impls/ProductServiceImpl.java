package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeFeaturedDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeRecentDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.PhotoService;
import az.edu.itbrains.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final PhotoService photoService;



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

        List<Product> findProducts = productRepository.findTop8ByOrderByIdDesc();
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
}
