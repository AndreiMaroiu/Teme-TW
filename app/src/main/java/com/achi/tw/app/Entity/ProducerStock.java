package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProducerStock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private Float price;

    @OneToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "user_id")
    private User producer;
}
