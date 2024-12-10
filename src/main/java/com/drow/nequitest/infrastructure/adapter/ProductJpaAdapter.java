package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.spi.IProductPersistencePort;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import com.drow.nequitest.infrastructure.out.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductJpaAdapter(IProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createProduct(ProductModel productModel) {
        ProductEntity productEntity = modelMapper.map(productModel, ProductEntity.class);
        productRepository.save(productEntity);
    }
}
