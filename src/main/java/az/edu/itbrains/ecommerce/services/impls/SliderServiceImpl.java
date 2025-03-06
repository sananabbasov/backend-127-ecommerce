package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.slider.SliderHomeDto;
import az.edu.itbrains.ecommerce.models.Slider;
import az.edu.itbrains.ecommerce.repositories.SliderRepository;
import az.edu.itbrains.ecommerce.services.SliderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<SliderHomeDto> getHomeSlider() {
        List<Slider> findSlider = sliderRepository.findTop3ByOrderByIndex();
        List<SliderHomeDto> sliders = findSlider.stream().map(slider -> modelMapper.map(slider, SliderHomeDto.class)).collect(Collectors.toList());
        return sliders;
    }
}
