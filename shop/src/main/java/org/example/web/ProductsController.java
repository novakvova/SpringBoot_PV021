package org.example.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.DTO.product.ProductImageSaveDTO;
import org.example.DTO.product.ProductItemDTO;
import org.example.entities.ProductImageEntity;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.ProductImageRepository;
import org.example.repositories.ProductRepository;
import org.example.storage.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Продукти")
@RequestMapping(path="api/products")
public class ProductsController {
    private final ProductRepository productRepository;
    private final ApplicationMapper mapper;
    private final StorageService storageService;
    private final ProductImageRepository productImageRepository;

    @GetMapping("list")
    public List<ProductItemDTO> index() {
        List<ProductItemDTO> products = mapper.productToProductItemDTO_List(productRepository.findAll());
        return products;
    }

    @PostMapping("upload")
    public ProductImageSaveDTO handleFileUpload(@RequestParam("productimage") MultipartFile file) {
        String fileName = storageService.store(file);

        ProductImageEntity productImage =
                new ProductImageEntity(fileName, 0);

        productImageRepository.save(productImage);

        return new ProductImageSaveDTO(productImage.getId(), fileName);
    }
}
