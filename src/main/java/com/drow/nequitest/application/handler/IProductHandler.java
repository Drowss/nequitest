package com.drow.nequitest.application.handler;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.dto.response.ProductResponseDto;
import com.drow.nequitest.application.dto.response.ProductStockbyBranchResponse;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;

import java.util.List;

public interface IProductHandler {
    void createProduct(ProductRequestDto productRequestDto);

    void addProductToSucursal(Integer productId, Integer sucursalId);

    void deleteProductFromBranch(Integer productId, Integer branchId);

    void updateStock(Integer productId, Integer stock);

    void updateName(Integer productId, String name);

    ProductResponseDto getProductStock();

    List<ProductStockbyBranchResponse> getProductStockByFranchise(Integer franchiseId);
}
