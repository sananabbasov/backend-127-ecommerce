package az.edu.itbrains.ecommerce.dtos.slider;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderHomeDto {
    private Long id;
    private String title;
    private String description;
    private String redirectUrl;
    private String photoUrl;
}
