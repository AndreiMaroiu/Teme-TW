package com.achi.tw.app.repositories;

import java.util.List;
import com.achi.tw.app.entity.ProducerStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProducerStockRepository extends CrudRepository<ProducerStock, Integer>
{
    @Query("SELECT s FROM ProducerStock s WHERE s.producer.id = :id")
    List<ProducerStock> getStocksByUser(@Param("id") Integer id);

    @Query("SELECT s FROM ProducerStock s WHERE s.name like %:name%")
    List<ProducerStock> getStocksByProductName(@Param("name") String name);
}
