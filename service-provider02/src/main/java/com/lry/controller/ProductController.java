package com.lry.controller;

import com.lry.pojo.Product;
import com.lry.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/list")
    public List<Product> selectProductList() {
        return productService.selectProductList();
    }

}
