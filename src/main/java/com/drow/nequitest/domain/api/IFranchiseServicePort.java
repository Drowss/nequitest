package com.drow.nequitest.domain.api;

import com.drow.nequitest.domain.model.FranchiseModel;

public interface IFranchiseServicePort {
    void createFranchise(FranchiseModel franchiseModel);

    void updateName(Integer franchiseId, String name);
}
