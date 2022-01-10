package com.achi.tw.app;

import com.achi.tw.app.Dao.ProductDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication
{
    public static void main(String[] args)
    {
        var products = ProductDao.getProducts();
        System.out.println(products);
        SpringApplication.run(AppApplication.class, args);
    }
}
