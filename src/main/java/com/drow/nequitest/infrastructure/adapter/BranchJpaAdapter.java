package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.BranchModel;
import com.drow.nequitest.domain.spi.IBranchPersistencePort;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BranchJpaAdapter implements IBranchPersistencePort {

    private final IBranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final IFranchiseRepository franchiseRepository;

    public BranchJpaAdapter(IBranchRepository branchRepository, ModelMapper modelMapper, IFranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public void createBranch(BranchModel branchModel) {
        branchRepository.save(modelMapper.map(branchModel, BranchEntity.class));
    }

    @Override
    public void addBranchToFranchise(Integer branchId, Integer franchiseId) {
        BranchEntity branch = branchRepository.findById(branchId).orElseThrow(() -> new FranchisesException("Branch not found"));
        FranchiseEntity franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchisesException("Franchise not found"));

        branch.getFranchises().add(franchise);
        branchRepository.save(branch);
    }
}
