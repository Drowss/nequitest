package com.drow.nequitest.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BranchRequestDto {
    @NotNull
    @NotEmpty
    private String name;


    public BranchRequestDto() {
    }

    public BranchRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
