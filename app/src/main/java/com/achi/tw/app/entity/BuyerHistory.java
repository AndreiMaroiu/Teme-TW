package com.achi.tw.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class BuyerHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private User buyer;

    @Column
    private Date date;

    @OneToMany(mappedBy = "history")
    private List<CartItem> items;

    @Column
    private boolean active;

    public float getTotal()
    {
        float total = 0.0f;

        for(var item : items)
        {
            total += item.getAmount() * item.getStock().getPrice();
        }

        return total;
    }
}
