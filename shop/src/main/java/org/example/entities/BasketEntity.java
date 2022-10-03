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

    @EmbeddedId
    private BasketId basketId;

}
