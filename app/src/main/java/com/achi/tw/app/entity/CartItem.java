package com.achi.tw.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "cart_item")
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private float productPrice;

    @Column
    private int amount;

    @OneToOne
    @JoinColumn(name = "trader_id", referencedColumnName = "user_id")
    private User trader;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private BuyerHistory history;
}
