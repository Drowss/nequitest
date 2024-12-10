package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.FranchiseRequestDto;
import com.drow.nequitest.application.handler.IFranchiseHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/franchise")
public class FranchiseController {

    private final IFranchiseHandler franchiseHandler;

    public FranchiseController(IFranchiseHandler franchiseHandler) {
        this.franchiseHandler = franchiseHandler;
    }

    @PostMapping("/create")
    public void createFranchise(@RequestBody @Valid FranchiseRequestDto franchiseRequestDto) {
        franchiseHandler.createFranchise(franchiseRequestDto);
    }

    @PatchMapping("/update-name")
    public ResponseEntity<?> updateName(@RequestParam("franchiseId") Integer franchiseId, @RequestParam("name") String name) {
        franchiseHandler.updateName(franchiseId, name);
        return ResponseEntity.ok("Name updated correctly");
    }
}
