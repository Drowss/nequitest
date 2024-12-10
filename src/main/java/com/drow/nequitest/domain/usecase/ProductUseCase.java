package com.drow.nequitest.domain.usecase;

import com.drow.nequitest.domain.api.IProductServicePort;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.spi.IProductPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }
    @Override
    public void createProduct(ProductModel productModel) {
        productPersistencePort.createProduct(productModel);
    }
}
