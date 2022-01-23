package com.achi.tw.app.dao;

import com.achi.tw.app.entity.TraderStock;

import java.util.List;

public class TraderStockDao
{
    public static List<TraderStock> findAll()
    {
        var result = DaoHelper.doTransaction(session ->
                session.createQuery("from TraderStock ", TraderStock.class).list());

        return (List<TraderStock>)result;
    }
}
