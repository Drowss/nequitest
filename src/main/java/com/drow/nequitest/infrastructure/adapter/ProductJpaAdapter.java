package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.dto.response.ProductStockbyBranchResponse;
import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.ProductModel;
import com.drow.nequitest.domain.model.ProductStockbyBranchModel;
import com.drow.nequitest.domain.spi.IProductPersistencePort;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import com.drow.nequitest.infrastructure.out.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.drow.nequitest.core.Constants.*;

@Service
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final IBranchRepository branchRepository;
    private final IFranchiseRepository franchiseRepository;
    private final ModelMapper modelMapper;

    public ProductJpaAdapter(IProductRepository productRepository, ModelMapper modelMapper, IBranchRepository branchRepository, IFranchiseRepository franchiseRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
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

    @Override
    public void updateStock(Integer productId, Integer stock) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new FranchisesException(PRODUCT_NOT_FOUND));

        product.setStock(stock);
        productRepository.save(product);
    }

    @Override
    public void updateName(Integer productId, String name) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new FranchisesException(PRODUCT_NOT_FOUND));

        product.setName(name);
        productRepository.save(product);
    }

    @Override
    public ProductModel getProductStock() {
        List<ProductEntity> products = productRepository.findAll();
        ProductEntity prod = products.stream()
                .max(Comparator.comparingInt(ProductEntity::getStock))
                .orElse(null);
        return modelMapper.map(prod, ProductModel.class);
    }

    @Override
    public List<ProductStockbyBranchModel> getProductStockByFranchise(Integer franchiseId) {
        FranchiseEntity franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException(FRANCHISE_NOT_FOUND));

        return franchise.getBranches().stream()
                .map(branch -> {
                    ProductEntity productWithMaxStock = branch.getProducts().stream()
                            .max((p1, p2) -> p1.getStock().compareTo(p2.getStock()))
                            .orElse(null);

                    if (productWithMaxStock != null) {
                        return new ProductStockbyBranchModel(
                                branch.getName(),
                                productWithMaxStock.getName(),
                                productWithMaxStock.getStock()
                        );
                    } else {
                        return null;
                    }
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
}
