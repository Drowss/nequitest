package com.drow.nequitest.application.handler;

import com.drow.nequitest.application.dto.request.BranchRequestDto;

public interface IBranchHandler {
    void createBranch(BranchRequestDto branchRequestDto);

    void addBranchToFranchise(Integer branchId, Integer franchiseId);

    void updateName(Integer branchId, String name);
}
