package az.edu.itbrains.ecommerce.dtos.photo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
    private Long id;
    private String photoUrl;
}
