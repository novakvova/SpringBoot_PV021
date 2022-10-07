package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double priceBuy;
    private int count;
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private OrderEntity order;
    public OrderItemEntity() {
    }
    public OrderItemEntity(double priceBuy, int count, ProductEntity product, OrderEntity order) {
        this.priceBuy = priceBuy;
        this.count = count;
        this.product = product;
        this.order = order;
    }
}
