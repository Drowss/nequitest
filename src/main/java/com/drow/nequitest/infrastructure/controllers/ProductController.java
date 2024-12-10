package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.dto.response.ProductResponseDto;
import com.drow.nequitest.application.dto.response.ProductStockbyBranchResponse;
import com.drow.nequitest.application.handler.IProductHandler;
import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductHandler productHandler;

    public ProductController(IProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        productHandler.createProduct(productRequestDto);
    }

    @PostMapping("/add-product/sucursal")
    public ResponseEntity<?> addProductToSucursal(@RequestParam("productId") Integer productId, @RequestParam("sucursalId") Integer sucursalId) {
        productHandler.addProductToSucursal(productId, sucursalId);
        return ResponseEntity.ok("Product added correctly to the branch");
    }

    @DeleteMapping("/delete-product/sucursal")
    public ResponseEntity<?> deleteProduct(@RequestParam("productId") Integer productId, @RequestParam("branchId") Integer branchId) {
        productHandler.deleteProductFromBranch(productId, branchId);
        return ResponseEntity.ok("Product deleted from branch correctly");
    }

    @PatchMapping("/update-stock")
    public ResponseEntity<?> updateStock(@RequestParam("productId") Integer productId, @RequestParam("stock") Integer stock) {
        productHandler.updateStock(productId, stock);
        return ResponseEntity.ok("Stock updated correctly");
    }

    @PatchMapping("/update-name")
    public ResponseEntity<?> updateName(@RequestParam("productId") Integer productId, @RequestParam("name") String name) {
        productHandler.updateName(productId, name);
        return ResponseEntity.ok("Name updated correctly");
    }

    @GetMapping("/get-product/stock")
    public ResponseEntity<?> getProductStock() {
        ProductResponseDto productResponseDto = productHandler.getProductStock();
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/stock")
    public List<ProductStockbyBranchResponse> getProductStockByFranchise(@RequestParam("franchiseId") Integer franchiseId) {
        return productHandler.getProductStockByFranchise(franchiseId);
    }
}
