package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.FranchiseRequestDto;
import com.drow.nequitest.application.handler.IFranchiseHandler;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
