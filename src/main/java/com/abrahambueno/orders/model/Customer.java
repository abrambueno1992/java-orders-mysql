package com.abrahambueno.orders.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private double cashonhand;

    public Customer() {
    }
    public Customer(String name, double cashonhand)
    {
        this.name = name;
        this.cashonhand = cashonhand;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public double getCashonhand() {
        return cashonhand;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCashonhand(double cashonhand) {
        this.cashonhand = cashonhand;
    }
}
