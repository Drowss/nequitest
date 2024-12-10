package com.drow.nequitest.application.handler;

import com.drow.nequitest.application.dto.request.FranchiseRequestDto;

public interface IFranchiseHandler {
    void createFranchise(FranchiseRequestDto franchiseRequestDto);

    void updateName(Integer franchiseId, String name);
}
