package com.achi.tw.app.Dao;

import com.achi.tw.app.Entity.Product;

import java.util.ArrayList;
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
