package org.example.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.dto.product.ProductItemDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api( tags = "Продукти")
@RequestMapping(path="api/products")
public class ProductsController {
    private final ProductRepository productRepository;
    private final ApplicationMapper mapper;

    @GetMapping("list")
    public List<ProductItemDTO> index() {
        List<ProductItemDTO> products = mapper.productToProductItemDTO_List(productRepository.findAll());
        return products;
    }
}
