package com.achi.tw.app.Repositories;

import java.util.List;
import com.achi.tw.app.Entity.ProducerStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProducerStockRepository extends CrudRepository<ProducerStock, Integer>
{
    @Query("SELECT s FROM ProducerStock s WHERE s.producer.id = :id")
    List<ProducerStock> getStocksByUser(@Param("id") Integer id);
}
