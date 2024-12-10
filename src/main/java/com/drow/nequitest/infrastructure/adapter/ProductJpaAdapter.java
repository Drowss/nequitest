package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.spi.IProductPersistencePort;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import com.drow.nequitest.infrastructure.out.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final IBranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public ProductJpaAdapter(IProductRepository productRepository, ModelMapper modelMapper, IBranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public void createProduct(ProductModel productModel) {
        ProductEntity productEntity = modelMapper.map(productModel, ProductEntity.class);
        productRepository.save(productEntity);
    }

    @Override
    public void addProductToSucursal(Integer productId, Integer sucursalId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new FranchisesException("Product not found"));
        BranchEntity branch = branchRepository.findById(sucursalId).orElseThrow(() -> new FranchisesException("Branch not found"));

        product.getBranches().add(branch);
        productRepository.save(product);
    }
}
