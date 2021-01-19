package com.lry.service.impl;

import com.lry.pojo.Product;
import com.lry.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> selectProductList() {
        System.out.println("service-provider被调用");
        return Arrays.asList(new Product(1, "华为手机", 2, 5888D),
                new Product(2, "联想笔记本", 1, 51888D),
                new Product(3, "小米平板", 5, 52888D)
        );
    }
}
