package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_baskets")
public class BasketEntity {
    private int count;
    @EmbeddedId
    private BasketId basketId;
    public BasketEntity() {
    }
    public BasketEntity(int count) {
        this.count = count;
    }
}
