package az.edu.itbrains.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private Double price;
    private Double priceDiscount;
    private int quantity;
    private String information;
    private Date createDate;
    private Date updateDate;
    private boolean featured;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Photo> photos;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();
}
