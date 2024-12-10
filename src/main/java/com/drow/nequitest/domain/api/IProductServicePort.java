package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.ProductModel;

public interface IProductServicePort {
    void createProduct(ProductModel productModel);

    void addProductToSucursal(Integer productId, Integer sucursalId);
}
