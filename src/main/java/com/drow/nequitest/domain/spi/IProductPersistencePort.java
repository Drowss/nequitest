package com.drow.nequitest.domain.spi;

import com.drow.nequitest.domain.model.ProductModel;

public interface IProductPersistencePort {
    void createProduct(ProductModel productModel);

    void addProductToSucursal(Integer productId, Integer sucursalId);
}
