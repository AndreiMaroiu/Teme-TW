package com.achi.tw.app.Repositories;

import com.achi.tw.app.Entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Integer>
{
}
