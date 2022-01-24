package com.achi.tw.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "producer_stock")
public class ProducerStock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private Float price;

    @OneToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "user_id")
    private User producer;

    public String getName()
    {
        return product.getName();
    }
}
