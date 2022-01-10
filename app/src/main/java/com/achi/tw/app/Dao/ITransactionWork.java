package com.achi.tw.app.Dao;

import org.hibernate.Session;

public interface ITransactionWork
{
    Object doWork(Session session);
}
