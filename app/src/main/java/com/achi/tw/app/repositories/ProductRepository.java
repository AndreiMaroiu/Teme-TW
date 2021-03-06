package com.achi.tw.app.repositories;

import com.achi.tw.app.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Integer>
{
    @Query("select p from Product p where p.id = :id")
    Product getProductById(@Param("id") Integer id);
}
