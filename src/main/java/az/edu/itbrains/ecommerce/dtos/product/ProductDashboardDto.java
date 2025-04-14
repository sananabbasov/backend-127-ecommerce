package az.edu.itbrains.ecommerce.dtos.product;

import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDashboardDto {
    private Long id;
    private String name;
    private Double price;
    private Double priceDiscount;
    private int quantity;
    private boolean featured;
    private CategoryDto category;
}
