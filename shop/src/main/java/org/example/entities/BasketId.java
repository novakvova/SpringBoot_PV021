package org.example.entities;

import lombok.Data;
import org.springframework.data.mapping.KPropertyPathExtensionsKt;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class BasketId implements Serializable {
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;
}
