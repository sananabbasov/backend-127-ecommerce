package az.edu.itbrains.ecommerce.dtos.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryHomeDto {
    private Long id;
    private String name;
    private String photoUrl;
    private int productCount;
}
