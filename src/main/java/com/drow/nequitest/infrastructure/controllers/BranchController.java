package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.BranchRequestDto;
import com.drow.nequitest.application.handler.IBranchHandler;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branch")
public class BranchController {

    private final IBranchHandler branchHandler;

    public BranchController(IBranchHandler branchHandler) {
        this.branchHandler = branchHandler;
    }

    @PostMapping("/create")
    public void createBranch(@RequestBody @Valid BranchRequestDto branchRequestDto) {
        branchHandler.createBranch(branchRequestDto);
    }
}
