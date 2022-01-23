package com.achi.tw.app.dao;

import com.achi.tw.app.entity.User;

public class UserDao
{
    public static User findByUserName(String username)
    {
        return (User)DaoHelper.doTransaction(session ->
                session.createQuery("from User u where u.username = :username")
                        .setParameter("username", username)
                        .stream().findFirst());
    }

    public static void addUser(User user)
    {
        DaoHelper.doTransaction(session ->
                session.save(user));
    }
}
