package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.BranchRequestDto;
import com.drow.nequitest.application.handler.IBranchHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBranchHandler branchHandler;

    @Test
    void createBranch_shouldCallHandler() throws Exception {
        String requestBody = """
            {
                "name": "New Branch"
            }
        """;

        mockMvc.perform(post("/api/v1/branch/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(branchHandler, times(1)).createBranch(Mockito.any(BranchRequestDto.class));
    }

    @Test
    void createBranch_shouldReturnBadRequestForInvalidInput() throws Exception {
        String requestBody = """
            {
                "name": ""
            }
        """;

        mockMvc.perform(post("/api/v1/branch/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());
    }

    @Test
    void addBranchToFranchise_shouldCallHandler() throws Exception {
        Integer branchId = 1;
        Integer franchiseId = 2;

        mockMvc.perform(post("/api/v1/branch/add-branch/franchise")
                        .param("branchId", branchId.toString())
                        .param("franchiseId", franchiseId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Branch added correctly to the franchise"));

        verify(branchHandler, times(1)).addBranchToFranchise(branchId, franchiseId);
    }

    @Test
    void addBranchToFranchise_shouldReturnBadRequestForMissingParams() throws Exception {
        mockMvc.perform(post("/api/v1/branch/add-branch/franchise"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateName_shouldCallHandler() throws Exception {
        Integer branchId = 1;
        String name = "Updated Branch Name";

        mockMvc.perform(patch("/api/v1/branch/update-name")
                        .param("branchId", branchId.toString())
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(content().string("Name updated correctly"));

        verify(branchHandler, times(1)).updateName(branchId, name);
    }

    @Test
    void updateName_shouldReturnBadRequestForMissingParams() throws Exception {
        mockMvc.perform(patch("/api/v1/branch/update-name"))
                .andExpect(status().isBadRequest());
    }

}