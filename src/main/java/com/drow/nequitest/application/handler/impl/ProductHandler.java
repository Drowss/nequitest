package com.drow.nequitest.application.handler.impl;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.handler.IProductHandler;
import com.drow.nequitest.domain.api.IProductServicePort;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductHandler implements IProductHandler {

    private final IProductServicePort productServicePort;
    private final ModelMapper modelMapper;

    public ProductHandler(IProductServicePort productServicePort, ModelMapper modelMapper) {
        this.productServicePort = productServicePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        productServicePort.createProduct(modelMapper.map(productRequestDto, ProductModel.class));
    }
}
