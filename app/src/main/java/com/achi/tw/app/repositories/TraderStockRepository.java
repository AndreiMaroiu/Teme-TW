package com.achi.tw.app.repositories;

import com.achi.tw.app.entity.TraderStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraderStockRepository extends CrudRepository<TraderStock, Integer>
{
    @Query("SELECT s FROM TraderStock s WHERE s.trader.id = :id")
    List<TraderStock> getStocksByUser(@Param("id") Integer id);

    @Query("SELECT s FROM TraderStock s WHERE s.amount > 0")
    List<TraderStock> findAllAvailable();

    @Query("SELECT s FROM TraderStock s WHERE s.id = :id")
    TraderStock getStockById(@Param("id") Integer id);

    @Query("SELECT s FROM TraderStock s WHERE s.product.name = :name and s.amount > 0")
    List<TraderStock> findStocksByProductName(@Param("name") String name);

    @Query("SELECT s FROM TraderStock s WHERE s.price < :price and s.product.name = :name and s.amount > 0")
    List<TraderStock> findAllByNameAndPrice(Float price, String name);

    @Query("SELECT s FROM TraderStock s WHERE s.price < :price and s.amount > 0")
    List<TraderStock> findAllByPrice(Float price);
}
