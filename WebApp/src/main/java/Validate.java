import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Validate {

    private static SessionFactory sessionFactory = null;
    private static final StandardServiceRegistry registry;

    static {
        registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e)
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

    public static void addUser(User user)
    {
        try
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
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
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

            return false;
        }
    }
}
