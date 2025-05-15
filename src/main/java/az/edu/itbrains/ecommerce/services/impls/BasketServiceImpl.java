package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.basket.BasketAddDto;
import az.edu.itbrains.ecommerce.dtos.basket.BasketDto;
import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.models.User;
import az.edu.itbrains.ecommerce.repositories.BasketRepository;
import az.edu.itbrains.ecommerce.services.BasketService;
import az.edu.itbrains.ecommerce.services.ProductService;
import az.edu.itbrains.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public boolean addToCart(String name, BasketAddDto basketAddDto) {
        try {
            User findUser = userService.findUserByEmail(name);
            Product findProduct = productService.findProductById(basketAddDto.getProductId());
            Basket findBasket = basketRepository.findByProductIdAndUserId(basketAddDto.getProductId(), findUser.getId());
            if (findBasket != null){
                findBasket.setQuantity(findBasket.getQuantity()+ basketAddDto.getQuantity());
                basketRepository.save(findBasket);
            }else{
                Basket basket = new Basket();
                basket.setUser(findUser);
                basket.setProduct(findProduct);
                basket.setQuantity(basketAddDto.getQuantity());
                basketRepository.save(basket);
            }

            return true;
        }catch (Exception error){
            return false;
        }
    }

    @Override
    public List<BasketDto> getUserBaskets() {
        List<Basket> basketList = basketRepository.findAll();
        List<BasketDto> basketDtoList = basketList.stream().map(basket -> modelMapper.map(basket,BasketDto.class)).collect(Collectors.toList());
        return basketDtoList;
    }
}
