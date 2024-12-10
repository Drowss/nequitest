package com.drow.nequitest.application.handler.impl;

import com.drow.nequitest.application.dto.request.FranchiseRequestDto;
import com.drow.nequitest.application.handler.IFranchiseHandler;
import com.drow.nequitest.domain.api.IFranchiseServicePort;
import com.drow.nequitest.domain.model.FranchiseModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FranchiseHandler implements IFranchiseHandler {

    private final IFranchiseServicePort franchiseServicePort;
    private final ModelMapper modelMapper;

    public FranchiseHandler(IFranchiseServicePort franchiseServicePort, ModelMapper modelMapper) {
        this.franchiseServicePort = franchiseServicePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createFranchise(FranchiseRequestDto franchiseRequestDto) {
        franchiseServicePort.createFranchise(modelMapper.map(franchiseRequestDto, FranchiseModel.class));
    }

    @Override
    public void updateName(Integer franchiseId, String name) {
        franchiseServicePort.updateName(franchiseId, name);
    }
}
