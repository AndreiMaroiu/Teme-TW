package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.exception.*;

import java.util.List;

public class UserDao
{

    private static SessionFactory sessionFactory = null;
    private static final StandardServiceRegistry registry;

    static {
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

    public static User getUser(String username, String password)
    {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            List<User> result = session.createQuery("from User", User.class).list();

            for (User user : result)
            {
                if (user.getUsername().equals(username) && user.getPassword().equals(password))
                {
                    return user;
                }
            }

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean addUser(User user)
    {
        try
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
            session.close();

            return true;
        }
        catch (ConstraintViolationException e)
        {
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser(User user)
    {
        try
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
            session.close();

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static User findUserByName(String name)
    {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", name);

            List<User> result = query.list();

            session.getTransaction().commit();
            session.close();

            return result.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
