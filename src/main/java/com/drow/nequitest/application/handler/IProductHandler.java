package com.drow.nequitest.application.handler;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;

public interface IProductHandler {
    void createProduct(ProductRequestDto productRequestDto);
}
