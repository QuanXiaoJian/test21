package com.powernode.dao;

import com.powernode.pojo.Product;

import java.util.List;

public interface ProductDao {
    List getAll();

    int addPro(Product product);

    int deletePro(String id);

    Product getEdit(String id);

    int setPro(Product id);

    int deleteMore();



}
