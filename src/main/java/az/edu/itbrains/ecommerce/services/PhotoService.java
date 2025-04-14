package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    String findPhotoByProductId(Long id);

    void uploads(List<MultipartFile> files, Product product);
}
