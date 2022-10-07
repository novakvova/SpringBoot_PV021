package org.example.DTO.product;

import lombok.Data;

@Data
public class ProductImageSaveDTO {
    private int id;
    private String fileName;

    public ProductImageSaveDTO(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
}
