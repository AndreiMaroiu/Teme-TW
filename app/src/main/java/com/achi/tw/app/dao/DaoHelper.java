package com.achi.tw.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DaoHelper
{
    public static SessionFactory sessionFactory = null;
    public static final StandardServiceRegistry registry;

    static
    {
        registry = new StandardServiceRegistryBuilder().configure().build();

        try
        {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Session getSession()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static Object doTransaction(ITransactionWork method)
    {
        Session session = null;
        try
        {
            session = getSession();

            return method.doWork(session);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (session != null)
            {
                session.getTransaction().commit();
                session.close();
            }
        }

        return null;
    }
}
