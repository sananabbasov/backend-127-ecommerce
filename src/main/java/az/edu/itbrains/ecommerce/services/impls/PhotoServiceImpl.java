package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.models.Photo;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.repositories.PhotoRepository;
import az.edu.itbrains.ecommerce.services.PhotoService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;


    @Override
    public String findPhotoByProductId(Long id) {
        Photo findPhoto = photoRepository.findPhotoByCoverAndProductId(id);
        String photoUrl = findPhoto == null ? "https://cdn.pixabay.com/photo/2016/03/21/20/05/image-1271454_1280.png" : findPhoto.getPhotoUrl() ;
        return photoUrl;
    }

    @Override
    public void uploads(List<MultipartFile> files, Product product) {
        try {

            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", "backend127");
            int count = 0;
            for (MultipartFile file : files) {
                count++;
                Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
                String publicId = (String) uploadedFile.get("public_id");
                String photoUrl = cloudinary.url().secure(true).generate(publicId);
                Photo photo = new Photo();
                photo.setPhotoUrl(photoUrl);
                photo.setProduct(product);
                photo.setCover(count == 1);
                photoRepository.save(photo);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
