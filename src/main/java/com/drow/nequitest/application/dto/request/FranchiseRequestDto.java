package com.drow.nequitest.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class FranchiseRequestDto {
    @NotEmpty
    private String name;

    public FranchiseRequestDto() {
    }

    public FranchiseRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
