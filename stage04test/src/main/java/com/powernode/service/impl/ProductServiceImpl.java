package com.powernode.service.impl;

import com.powernode.dao.ProductDao;
import com.powernode.dao.impl.ProductDaoImpl;
import com.powernode.pojo.Product;
import com.powernode.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    public List getAll() {
        return productDao.getAll();
    }

    @Override
    public int addPro(Product product) {
        return productDao.addPro(product);
    }

    @Override
    public int deletePro(String id) {
        return productDao.deletePro(id);
    }

    @Override
    public Product getEdit(String id) {
        return productDao.getEdit(id);
    }

    @Override
    public int setPro(Product id) {
        return productDao.setPro(id);
    }

    @Override
    public boolean delAll(String[] ids) {
        if(ids!=null) {
            int i = 0;
            for (int i1 = 0; i1 < ids.length; i1++) {
                String id = ids[i1];
                i += productDao.deletePro(id);

            }
            if (i == ids.length) {
                return true;
            }

        }
        return false;
    }
}
