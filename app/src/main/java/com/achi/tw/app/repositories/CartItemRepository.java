package com.achi.tw.app.repositories;

import com.achi.tw.app.entity.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Integer>
{
    @Query("SELECT item from CartItem item WHERE item.history.buyer.id = :userId AND item.history.active = true")
    List<CartItem> findAllByUser(@Param("userId") Integer userId);
}
