package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.model.ProductStockbyBranchModel;

import java.util.List;

public interface IProductServicePort {
    void createProduct(ProductModel productModel);

    void addProductToSucursal(Integer productId, Integer sucursalId);

    void deleteProductFromBranch(Integer productId, Integer branchId);

    void updateStock(Integer productId, Integer stock);

    void updateName(Integer productId, String name);

    ProductModel getProductStock();

    List<ProductStockbyBranchModel> getProductStockByFranchise(Integer franchiseId);
}
