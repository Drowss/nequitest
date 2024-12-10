package com.drow.nequitest.domain.model;

public class ProductStockbyBranchModel {
    private String branchName;
    private String productName;
    private Integer stock;

    public ProductStockbyBranchModel() {
    }

    public ProductStockbyBranchModel(String branchName, String productName, Integer stock) {
        this.branchName = branchName;
        this.productName = productName;
        this.stock = stock;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
