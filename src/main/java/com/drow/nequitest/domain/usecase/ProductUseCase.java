package com.drow.nequitest.domain.usecase;

import com.drow.nequitest.domain.api.IProductServicePort;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.model.ProductStockbyBranchModel;
import com.drow.nequitest.domain.spi.IProductPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void addProductToSucursal(Integer productId, Integer sucursalId) {
        productPersistencePort.addProductToSucursal(productId, sucursalId);
    }

    @Override
    public void deleteProductFromBranch(Integer productId, Integer branchId) {
        productPersistencePort.deleteProductFromBranch(productId, branchId);
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        productPersistencePort.updateStock(productId, stock);
    }

    @Override
    public void updateName(Integer productId, String name) {
        productPersistencePort.updateName(productId, name);
    }

    @Override
    public ProductModel getProductStock() {
        return productPersistencePort.getProductStock();
    }

    @Override
    public List<ProductStockbyBranchModel> getProductStockByFranchise(Integer franchiseId) {
        return productPersistencePort.getProductStockByFranchise(franchiseId);
    }
}
