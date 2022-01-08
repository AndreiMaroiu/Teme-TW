package com.achi.tw.app.Dao;

import com.achi.tw.app.Entity.Product;

import java.util.ArrayList;

public class ProductDao
{
    public static ArrayList<Product> getProducts()
    {
        ArrayList<Product> result = null;

        result = (ArrayList<Product>)DaoHelper.doTransaction(session ->
            session.createQuery("from Product ", Product.class).list()
        );

        return result;
    }
}
