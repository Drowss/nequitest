package com.drow.nequitest.application.dto.response;

public class ProductResponseDto {
    private String name;
    private Integer stock;

    public ProductResponseDto() {
    }

    public ProductResponseDto(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
