package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.BranchModel;

public interface IBranchServicePort {
    void createBranch(BranchModel branchModel);
}
