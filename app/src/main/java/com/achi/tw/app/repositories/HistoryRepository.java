package com.achi.tw.app.repositories;

import com.achi.tw.app.entity.BuyerHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends CrudRepository<BuyerHistory, Integer>
{
    @Query("SELECT h from BuyerHistory h where h.buyer.id = :id")
    public List<BuyerHistory> findAllByBuyer(@Param("id") int id);
}
