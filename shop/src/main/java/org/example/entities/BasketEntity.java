package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="tblBaskets")
public class BasketEntity {

    private int count;

    @ManyToOne
    @EmbeddedId
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @EmbeddedId
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;

}
