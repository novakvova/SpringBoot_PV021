package org.example.DTO.order;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class OrderItemDTO {
    private int id;
    private Date dateCreated;
}
