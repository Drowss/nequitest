package com.drow.nequitest.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Integer stock;

    public ProductRequestDto(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public ProductRequestDto() {
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
