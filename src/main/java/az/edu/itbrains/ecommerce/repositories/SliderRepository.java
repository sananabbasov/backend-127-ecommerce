package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Slider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SliderRepository extends JpaRepository<Slider, Long> {
    List<Slider> findTop3ByOrderByIndex();
}
