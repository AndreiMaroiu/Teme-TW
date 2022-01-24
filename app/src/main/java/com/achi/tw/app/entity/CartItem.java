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

    @Column
    private int amount;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private BuyerHistory history;

    @OneToOne
    @JoinColumn
    private TraderStock stock;
}
