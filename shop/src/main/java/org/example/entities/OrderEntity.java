package org.example.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="tbl_orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    private String number;
    @Column(nullable = false)
    private Date dateCreated;

    @ManyToOne
//    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private OrderStatusEntity status;


    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;
    public OrderEntity() {
    }

    public OrderEntity(Date dateCreated, UserEntity user, OrderStatusEntity status) {
        this.dateCreated = dateCreated;
        this.user = user;
        this.status = status;
    }


}
