package com.powernode.service;

import com.powernode.pojo.Product;

import java.util.List;

public interface ProductService {
    List getAll();

    int addPro(Product product);

    int deletePro(String id);

    Product getEdit(String id);

    int setPro(Product id);


    boolean delAll(String[] id);
}
