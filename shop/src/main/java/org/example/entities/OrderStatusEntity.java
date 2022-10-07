package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="tbl_order_statuses")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;
    public OrderStatusEntity() {
    }
    public OrderStatusEntity(String name) {
        this.name = name;
    }
}
