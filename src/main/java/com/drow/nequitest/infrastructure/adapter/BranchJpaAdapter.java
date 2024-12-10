package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.domain.model.BranchModel;
import com.drow.nequitest.domain.spi.IBranchPersistencePort;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BranchJpaAdapter implements IBranchPersistencePort {

    private final IBranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public BranchJpaAdapter(IBranchRepository branchRepository, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createBranch(BranchModel branchModel) {
        branchRepository.save(modelMapper.map(branchModel, BranchEntity.class));
    }
}
