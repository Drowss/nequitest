package com.drow.nequitest.domain.spi;

import com.drow.nequitest.domain.model.BranchModel;

public interface IBranchPersistencePort {
    void createBranch(BranchModel branchModel);

    void addBranchToFranchise(Integer branchId, Integer franchiseId);

    void updateName(Integer branchId, String name);
}
