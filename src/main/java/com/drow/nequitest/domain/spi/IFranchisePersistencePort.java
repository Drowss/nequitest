package com.drow.nequitest.domain.spi;

import com.drow.nequitest.domain.model.FranchiseModel;

public interface IFranchisePersistencePort {
    void createFranchise(FranchiseModel franchiseModel);

    void updateName(Integer franchiseId, String name);
}
