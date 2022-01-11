package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    private Integer amount;

    public String getAmountString()
    {
        if (amount == null)
        {
            return "Unlimited";
        }

        return amount.toString();
    }
}
