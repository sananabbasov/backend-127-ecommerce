package az.edu.itbrains.ecommerce.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    @NotBlank(message = "Məhsul adı boş ola bilməz")
    @Size(min = 3, max = 100, message = "Məhsul adı 3 ilə 100 simvol arasında olmalıdır")
    private String name;
    private Double price;
    private Double priceDiscount;
    private int quantity;
    private boolean featured;
    private Long categoryId;
}
