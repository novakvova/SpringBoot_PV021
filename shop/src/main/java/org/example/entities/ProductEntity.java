package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name ="tblProducts")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;
    private double price;
    @Column(length = 4000)
    private String description;
    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> images;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "basketId.product")
    private List<BasketEntity> baskets;

    public ProductEntity() {
    }

    public ProductEntity(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
