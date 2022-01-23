package com.achi.tw.app.dao;

import org.hibernate.Session;

public interface ITransactionWork
{
    Object doWork(Session session);
}
