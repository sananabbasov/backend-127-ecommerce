package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.models.Photo;
import az.edu.itbrains.ecommerce.repositories.PhotoRepository;
import az.edu.itbrains.ecommerce.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;

    @Override
    public String findPhotoByProductId(Long id) {
        Photo findPhoto = photoRepository.findPhotoByCoverAndProductId(id);
        String photoUrl = findPhoto == null ? "https://cdn.pixabay.com/photo/2016/03/21/20/05/image-1271454_1280.png" : findPhoto.getPhotoUrl() ;
        return photoUrl;
    }
}
