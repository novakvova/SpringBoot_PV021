package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="tblOrderItems")
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
}
