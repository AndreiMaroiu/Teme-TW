package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "producer_stock")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "user_id")
    private User producer;
}
