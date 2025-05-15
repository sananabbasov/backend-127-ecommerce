package az.edu.itbrains.ecommerce.dtos.basket;


import az.edu.itbrains.ecommerce.dtos.product.ProductBasketDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private Long id;
    private ProductBasketDto product;
    private int quantity;
    private double totalPrice;

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
