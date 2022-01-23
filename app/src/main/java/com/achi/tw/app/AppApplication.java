package com.achi.tw.app;

import com.achi.tw.app.dao.TraderStockDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppApplication.class, args);

        // just for testing
        for (var stock : TraderStockDao.findAll())
        {
            System.out.println(stock);
        }
    }
}
