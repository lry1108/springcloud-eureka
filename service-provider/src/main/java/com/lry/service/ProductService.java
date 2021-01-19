package com.lry.service;

import com.lry.pojo.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询商品列表
     * @return
     */
    List<Product> selectProductList();
}
