package az.edu.itbrains.ecommerce.dtos.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    @NotBlank(message = "Məhsul adı boş ola bilməz")
    @Size(min = 3, max = 100, message = "Məhsul adı 3 ilə 100 simvol arasında olmalıdır")
    private String name;

    @NotNull(message = "Qiymət boş ola bilməz")
    @Positive(message = "Qiymət müsbət ədəd olmalıdır")
    @DecimalMin(value = "0.01", message = "Qiymət ən azı 0.01 olmalıdır")
    private Double price;

    @PositiveOrZero(message = "Endirimli qiymət ya müsbət ədəd, ya da 0 olmalıdır")
    private Double priceDiscount;

    @NotNull(message = "Miqdar boş ola bilməz")
    @Min(value = 0, message = "Miqdar ən azı 0 ola bilər")
    @Max(value = 10000, message = "Miqdar ən çox 10000 ola bilər")
    private int quantity;

    private String shortDescription;
    private String description;

    private boolean featured;

    private List<MultipartFile> files;

    @NotNull(message = "Kateqoriya ID boş ola bilməz")
    private Long categoryId;
}
