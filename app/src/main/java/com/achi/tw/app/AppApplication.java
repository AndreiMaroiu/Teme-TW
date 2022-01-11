package com.achi.tw.app;

import com.achi.tw.app.Dao.ProductDao;
import com.achi.tw.app.Repositories.RoleRepository;
import com.achi.tw.app.Repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.relation.Role;

@SpringBootApplication
public class AppApplication
{
    public static void main(String[] args)
    {
        var configApp = SpringApplication.run(AppApplication.class, args);;
        RoleRepository roleRepository = configApp.getBean(RoleRepository.class);
        UserRepository userRepository = configApp.getBean(UserRepository.class);

        var roles = roleRepository.findAll();

        for (var role : roles)
        {
            System.out.println(role.getUsers());
        }

        var users = userRepository.findAll();

        for (var user : users)
        {
            System.out.println(user.getUsername());
        }
    }
}
