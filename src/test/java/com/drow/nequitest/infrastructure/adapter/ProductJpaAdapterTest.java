package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import com.drow.nequitest.infrastructure.out.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static com.drow.nequitest.core.Constants.PRODUCT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductJpaAdapterTest {

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IBranchRepository branchRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductJpaAdapter productJpaAdapter;

    @Test
    void createProduct_shouldSaveProductEntity() {
        ProductModel productModel = new ProductModel("Product A", 10);
        ProductEntity productEntity = new ProductEntity();
        when(modelMapper.map(productModel, ProductEntity.class)).thenReturn(productEntity);

        productJpaAdapter.createProduct(productModel);

        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void addProductToSucursal_shouldAddBranchToProduct() {
        Integer productId = 1;
        Integer branchId = 2;

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBranches(new ArrayList<>());

        BranchEntity branchEntity = new BranchEntity();

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branchEntity));

        productJpaAdapter.addProductToSucursal(productId, branchId);

        assertTrue(productEntity.getBranches().contains(branchEntity));
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void addProductToSucursal_shouldThrowProductNotFoundException() {
        Integer productId = 1;
        Integer branchId = 2;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        FranchisesException exception = assertThrows(FranchisesException.class,
                () -> productJpaAdapter.addProductToSucursal(productId, branchId));

        assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void deleteProductFromBranch_shouldRemoveBranchAndProductReferences() {
        Integer productId = 1;
        Integer branchId = 2;

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBranches(new ArrayList<>());
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setProducts(new ArrayList<>());

        productEntity.getBranches().add(branchEntity);
        branchEntity.getProducts().add(productEntity);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branchEntity));

        productJpaAdapter.deleteProductFromBranch(productId, branchId);

        assertFalse(productEntity.getBranches().contains(branchEntity));
        assertFalse(branchEntity.getProducts().contains(productEntity));
        verify(productRepository, times(1)).save(productEntity);
        verify(branchRepository, times(1)).save(branchEntity);
    }

    @Test
    void updateStock_shouldUpdateProductStock() {
        Integer productId = 1;
        Integer newStock = 20;

        ProductEntity productEntity = new ProductEntity();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        productJpaAdapter.updateStock(productId, newStock);

        assertEquals(newStock, productEntity.getStock());
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void updateName_shouldUpdateProductName() {
        Integer productId = 1;
        String newName = "Updated Product";

        ProductEntity productEntity = new ProductEntity();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        productJpaAdapter.updateName(productId, newName);

        assertEquals(newName, productEntity.getName());
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    void updateName_shouldThrowProductNotFoundException() {
        Integer productId = 1;
        String newName = "Updated Product";

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        FranchisesException exception = assertThrows(FranchisesException.class,
                () -> productJpaAdapter.updateName(productId, newName));

        assertEquals(PRODUCT_NOT_FOUND, exception.getMessage());
    }

}