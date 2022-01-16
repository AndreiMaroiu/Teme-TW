package com.achi.tw.app.Repositories;

import java.util.List;
import com.achi.tw.app.Entity.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Stock, Integer>
{
    @Query("SELECT s FROM producer_stock s WHERE s.producer.id = :id")
    public List<Stock> getStocksByUser(@Param("id") Integer id);
}
