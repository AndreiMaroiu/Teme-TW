package com.achi.tw.app.Repositories;

import com.achi.tw.app.Entity.ProducerStock;
import com.achi.tw.app.Entity.TraderStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraderStockRepository extends CrudRepository<TraderStock, Integer>
{
    @Query("SELECT s FROM TraderStock s WHERE s.trader.id = :id")
    List<TraderStock> getStocksByUser(@Param("id") Integer id);

    @Query("SELECT s FROM TraderStock s WHERE s.id = :id")
    TraderStock getStockById(@Param("id") Integer id);

    @Query("SELECT s FROM TraderStock s WHERE s.name = :name")
    List<TraderStock> findStocksByProductName(@Param("name") String name);

    @Query("SELECT s FROM TraderStock s WHERE s.price < :price and s.name = :name")
    List<TraderStock> findAllByNameAndPrice(Float price, String name);

    @Query("SELECT s FROM TraderStock s WHERE s.price < :price")
    List<TraderStock> findAllByPrice(Float price);
}
