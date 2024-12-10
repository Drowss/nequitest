package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.BranchModel;

public interface IBranchServicePort {
    void createBranch(BranchModel branchModel);

    void addBranchToFranchise(Integer branchId, Integer franchiseId);

    void updateName(Integer branchId, String name);
}
