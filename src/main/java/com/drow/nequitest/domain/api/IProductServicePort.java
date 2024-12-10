package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.ProductModel;

public interface IProductServicePort {
    void createProduct(ProductModel productModel);

    void addProductToSucursal(Integer productId, Integer sucursalId);

    void deleteProductFromBranch(Integer productId, Integer branchId);

    void updateStock(Integer productId, Integer stock);

    void updateName(Integer productId, String name);
}
