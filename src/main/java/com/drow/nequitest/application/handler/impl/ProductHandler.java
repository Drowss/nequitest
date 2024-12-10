package com.drow.nequitest.application.handler.impl;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.dto.response.ProductResponseDto;
import com.drow.nequitest.application.dto.response.ProductStockbyBranchResponse;
import com.drow.nequitest.application.handler.IProductHandler;
import com.drow.nequitest.domain.api.IProductServicePort;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.model.ProductStockbyBranchModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public void addProductToSucursal(Integer productId, Integer sucursalId) {
        productServicePort.addProductToSucursal(productId, sucursalId);
    }

    @Override
    public void deleteProductFromBranch(Integer productId, Integer branchId) {
        productServicePort.deleteProductFromBranch(productId, branchId);
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        productServicePort.updateStock(productId, stock);
    }

    @Override
    public void updateName(Integer productId, String name) {
        productServicePort.updateName(productId, name);
    }

    @Override
    public ProductResponseDto getProductStock() {
        ProductModel productModel = productServicePort.getProductStock();
        return modelMapper.map(productModel, ProductResponseDto.class);
    }

    @Override
    public List<ProductStockbyBranchResponse> getProductStockByFranchise(Integer franchiseId) {
        List<ProductStockbyBranchModel> productStockbyBranchModels = productServicePort.getProductStockByFranchise(franchiseId);
        return productStockbyBranchModels.stream()
                .map(productStock -> modelMapper.map(productStock, ProductStockbyBranchResponse.class))
                .toList();
    }
}
