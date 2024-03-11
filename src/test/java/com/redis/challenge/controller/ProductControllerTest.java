package com.redis.challenge.controller;

import com.redis.challenge.model.Product;
import com.redis.challenge.service.ProductService;
import com.redis.challenge.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private EntityManager entityManager;
    @MockBean
    private RedisService redisService;

    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void save() throws Exception {
        Product product = new Product(1, "Produto", "Descricao", 99.99);
        given(productService.save(any(Product.class))).willReturn(product);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        then(productService).should().save(any(Product.class));
    }

    @Test
    void getAll() {
        List<Product> products = Collections.singletonList(new Product());
        given(productService.getAll()).willReturn(products);

        List<Product> result = productController.getAll();

        assertNotNull(result);
        assertEquals(products, result);

        then(productService).should().getAll();
    }

    @Test
    void getById() throws Exception {
        Long productId = 1L;
        given(productService.getById(productId)).willReturn(Optional.of(new Product(1, "Produto", "Descricao", 99.99)));

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        then(productService).should().getById(productId);
    }

    @Test
    void deleteById() throws Exception {
        Long productId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productId))
                .andExpect(status().isOk());

        then(productService).should().deleteById(productId);
    }
}
