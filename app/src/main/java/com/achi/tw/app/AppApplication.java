package com.achi.tw.app;

import com.achi.tw.app.Repositories.RoleRepository;
import com.achi.tw.app.Repositories.ProducerStockRepository;
import com.achi.tw.app.Repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication
{
    public static void main(String[] args)
    {
        var configApp = SpringApplication.run(AppApplication.class, args);;
        RoleRepository roleRepository = configApp.getBean(RoleRepository.class);
        UserRepository userRepository = configApp.getBean(UserRepository.class);
        ProducerStockRepository stockRepository = configApp.getBean(ProducerStockRepository.class);

        var stocks = stockRepository.findAll();
        for (var stock : stocks)
        {
            System.out.println(stock);
        }
    }
}
