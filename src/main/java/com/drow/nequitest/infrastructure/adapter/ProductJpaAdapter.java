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

import static com.drow.nequitest.core.Constants.BRANCH_NOT_FOUND;
import static com.drow.nequitest.core.Constants.PRODUCT_NOT_FOUND;

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
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new FranchisesException(PRODUCT_NOT_FOUND));
        BranchEntity branch = branchRepository.findById(sucursalId).orElseThrow(() -> new FranchisesException(BRANCH_NOT_FOUND));

        product.getBranches().add(branch);
        productRepository.save(product);
    }

    @Override
    public void deleteProductFromBranch(Integer productId, Integer branchId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new FranchisesException(PRODUCT_NOT_FOUND));
        BranchEntity branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new FranchisesException(BRANCH_NOT_FOUND));

        product.getBranches().remove(branch);
        branch.getProducts().remove(product);

        productRepository.save(product);
        branchRepository.save(branch);
    }
}
