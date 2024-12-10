package com.drow.nequitest.domain.usecase;

import com.drow.nequitest.domain.api.IBranchServicePort;
import com.drow.nequitest.domain.model.BranchModel;
import com.drow.nequitest.domain.spi.IBranchPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class BranchUseCase implements IBranchServicePort {

    private final IBranchPersistencePort branchPersistencePort;

    public BranchUseCase(IBranchPersistencePort branchPersistencePort) {
        this.branchPersistencePort = branchPersistencePort;
    }

    @Override
    public void createBranch(BranchModel branchModel) {
        branchPersistencePort.createBranch(branchModel);
    }
}