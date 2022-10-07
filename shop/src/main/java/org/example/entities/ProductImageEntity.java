package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_product_images")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255, nullable = false)
    private String name;
    private int priority;
    @ManyToOne
    @JoinColumn(name="product_id", nullable = true)
    private ProductEntity product;

    public ProductImageEntity (){
    }
    public ProductImageEntity(String name, int priority, ProductEntity product) {
        this.name = name;
        this.priority = priority;
        this.product = product;
    }

    public ProductImageEntity(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
