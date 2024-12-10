package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.BranchRequestDto;
import com.drow.nequitest.application.handler.IBranchHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add-branch/franchise")
    public ResponseEntity<?> addProductToSucursal(@RequestParam("branchId") Integer branchId, @RequestParam("franchiseId") Integer franchiseId) {
        branchHandler.addBranchToFranchise(branchId, franchiseId);
        return ResponseEntity.ok("Branch added correctly to the franchise");
    }
}
