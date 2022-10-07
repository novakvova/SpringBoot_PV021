package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="tbl_products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255, nullable = false)
    private String name;

    private double price;

    @Column(length = 4000, nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> images;

//    @ManyToOne
//    @JoinColumn(name="category_id", nullable = false)
//    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "basketId.product")
    private List<BasketEntity> basket;

    public ProductEntity() {
    }
    public ProductEntity(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}


