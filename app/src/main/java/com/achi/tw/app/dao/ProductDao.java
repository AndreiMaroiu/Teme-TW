package com.achi.tw.app.dao;

import com.achi.tw.app.entity.Product;

import java.util.List;

public class ProductDao
{
    public static List<Product> getProducts()
    {
        Object result = DaoHelper.doTransaction(session ->
                session.createQuery("from Product ", Product.class).list()
        );

        return (List<Product>)result;
    }
}
