package com.drow.nequitest.application.handler.impl;

import com.drow.nequitest.application.dto.request.BranchRequestDto;
import com.drow.nequitest.application.handler.IBranchHandler;
import com.drow.nequitest.domain.api.IBranchServicePort;
import com.drow.nequitest.domain.model.BranchModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BranchHandler implements IBranchHandler {

    private final IBranchServicePort branchServicePort;
    private final ModelMapper modelMapper;

    public BranchHandler(IBranchServicePort branchServicePort, ModelMapper modelMapper) {
        this.branchServicePort = branchServicePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createBranch(BranchRequestDto branchRequestDto) {
        branchServicePort.createBranch(modelMapper.map(branchRequestDto, BranchModel.class));
    }
}
