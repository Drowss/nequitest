package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.FranchiseRequestDto;
import com.drow.nequitest.application.handler.IFranchiseHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FranchiseController.class)
class FranchiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFranchiseHandler franchiseHandler;

    @Test
    void createFranchise_shouldReturnOkWhenValidInput() throws Exception {
        String requestBody = """
            {
                "name": "Test Franchise"
            }
        """;

        mockMvc.perform(post("/api/v1/franchise/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(franchiseHandler).createFranchise(any(FranchiseRequestDto.class));
    }

    @Test
    void createFranchise_shouldReturnConflictWhenNameIsEmpty() throws Exception {
        String requestBody = """
            {
                "name": ""
            }
        """;

        mockMvc.perform(post("/api/v1/franchise/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());

        verifyNoInteractions(franchiseHandler);
    }

    @Test
    void createFranchise_shouldReturnConflictWhenNameIsMissing() throws Exception {
        String requestBody = "{}";

        mockMvc.perform(post("/api/v1/franchise/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());

        verifyNoInteractions(franchiseHandler);
    }

    @Test
    void updateName_shouldReturnOkWhenValidInput() throws Exception {
        Integer franchiseId = 1;
        String newName = "Updated Franchise Name";

        mockMvc.perform(patch("/api/v1/franchise/update-name")
                        .param("franchiseId", franchiseId.toString())
                        .param("name", newName))
                .andExpect(status().isOk())
                .andExpect(content().string("Name updated correctly"));

        verify(franchiseHandler).updateName(franchiseId, newName);
    }

    @Test
    void updateName_shouldReturnBadRequestWhenFranchiseIdIsMissing() throws Exception {
        String newName = "Updated Franchise Name";

        mockMvc.perform(patch("/api/v1/franchise/update-name")
                        .param("name", newName))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(franchiseHandler);
    }

    @Test
    void updateName_shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        Integer franchiseId = 1;

        mockMvc.perform(patch("/api/v1/franchise/update-name")
                        .param("franchiseId", franchiseId.toString()))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(franchiseHandler);
    }
}

