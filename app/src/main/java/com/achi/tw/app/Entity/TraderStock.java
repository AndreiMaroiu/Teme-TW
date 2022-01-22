package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "trader_stock")
public class TraderStock
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column
    private float price;

    @Column(name = "min_stock")
    private int minStock;

    @Column(name = "max_stock")
    private int maxStock;

    @Column(name = "current_stock")
    private int amount;

    @OneToOne
    @JoinColumn(name = "trader_id", referencedColumnName = "user_id")
    private User trader;

    public void buyFromStock(Integer howMuch)
    {
        amount -= howMuch;
    }
}
