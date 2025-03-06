package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM photos WHERE photos.cover = true and photos.product_id = :productId")
    Photo findPhotoByCoverAndProductId(@Param("productId") Long productId);
}
