package com.achi.tw.app.Repositories;

import com.achi.tw.app.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>
{
}
