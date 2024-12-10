package com.drow.nequitest.domain.usecase;

import com.drow.nequitest.domain.api.IFranchiseServicePort;
import com.drow.nequitest.domain.model.FranchiseModel;
import com.drow.nequitest.domain.spi.IFranchisePersistencePort;
import org.springframework.stereotype.Service;

@Service
public class FranchiseUseCase implements IFranchiseServicePort {

    private final IFranchisePersistencePort franchisePersistencePort;

    public FranchiseUseCase(IFranchisePersistencePort franchisePersistencePort) {
        this.franchisePersistencePort = franchisePersistencePort;
    }

    @Override
    public void createFranchise(FranchiseModel franchiseModel) {
        franchisePersistencePort.createFranchise(franchiseModel);
    }
}
