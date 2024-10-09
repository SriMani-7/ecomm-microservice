package com.microservices.product.service;

import static org.junit.jupiter.api.Assertions.*;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.product.service.controller.ProductController;
import com.microservices.product.service.service.ProductService;
import com.microservices.product.service.dto.ProductForm;

public class ProductServiceTest {
    
    private ProductController pController;
    private ProductService pService;
    private ProductForm form;
    
    @BeforeEach
    public void setUp() {
        pService = EasyMock.createMock(ProductService.class);
        pController = new ProductController();
        pController.setProductService(pService);
    }
    
    @Test
    public void addProductTest() {
       
        form = new ProductForm();
        EasyMock.expect(pService.addProduct(1L, form)).andReturn("Product added successfully");
        EasyMock.replay(pService);
        String response = pController.addProduct(1L, form);
        assertEquals("Product added successfully", response);
        EasyMock.verify(pService);
    }
}
