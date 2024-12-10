package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.handler.IProductHandler;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductHandler productHandler;

    public ProductController(IProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductRequestDto productRequestDto) {
        productHandler.createProduct(productRequestDto);
    }
}
