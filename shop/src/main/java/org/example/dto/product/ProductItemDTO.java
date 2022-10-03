package org.example.dto.product;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductItemDTO {
    private int id;
    private String name;
    private double price;
}
