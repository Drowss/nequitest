package com.drow.nequitest.infrastructure.controllers;

import com.drow.nequitest.application.dto.request.ProductRequestDto;
import com.drow.nequitest.application.handler.IProductHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private IProductHandler productHandler;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    // Setup MockMvc
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void createProduct_shouldReturnOk() throws Exception {
        String requestBody = """
            {
                "name": "Product 1",
                "stock": 100
            }
        """;

        mockMvc.perform(post("/api/v1/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(productHandler).createProduct(any(ProductRequestDto.class));
    }

    @Test
    void addProductToSucursal_shouldReturnOk() throws Exception {
        Integer productId = 1;
        Integer sucursalId = 1;

        mockMvc.perform(post("/api/v1/product/add-product/sucursal")
                        .param("productId", productId.toString())
                        .param("sucursalId", sucursalId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Product added correctly to the branch"));

        verify(productHandler).addProductToSucursal(productId, sucursalId);
    }

    @Test
    void deleteProduct_shouldReturnOk() throws Exception {
        Integer productId = 1;
        Integer branchId = 1;

        mockMvc.perform(delete("/api/v1/product/delete-product/sucursal")
                        .param("productId", productId.toString())
                        .param("branchId", branchId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted from branch correctly"));

        verify(productHandler).deleteProductFromBranch(productId, branchId);
    }

    @Test
    void updateStock_shouldReturnOk() throws Exception {
        Integer productId = 1;
        Integer stock = 150;

        mockMvc.perform(patch("/api/v1/product/update-stock")
                        .param("productId", productId.toString())
                        .param("stock", stock.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock updated correctly"));

        verify(productHandler).updateStock(productId, stock);
    }

    @Test
    void updateName_shouldReturnOk() throws Exception {
        Integer productId = 1;
        String name = "Updated Product";

        mockMvc.perform(patch("/api/v1/product/update-name")
                        .param("productId", productId.toString())
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(content().string("Name updated correctly"));

        verify(productHandler).updateName(productId, name);
    }

    @Test
    void createProduct_shouldReturnBadRequestForInvalidInput() throws Exception {
        String requestBody = """
            {
                "name": "",
                "stock": 100
            }
        """;

        mockMvc.perform(post("/api/v1/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

}