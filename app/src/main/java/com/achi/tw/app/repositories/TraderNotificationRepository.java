package com.achi.tw.app.repositories;

import com.achi.tw.app.entity.TraderNotification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraderNotificationRepository extends CrudRepository<TraderNotification, Integer>
{
    @Query("SELECT n FROM TraderNotification n WHERE n.trader.id = :id")
    List<TraderNotification> findAllByUser(@Param("id") Integer id);
}
