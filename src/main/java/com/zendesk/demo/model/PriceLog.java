package com.zendesk.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PriceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    private double price;

    private Date time;

    public long getId() {
        return id;
    }

    public PriceLog setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public PriceLog setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PriceLog setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public PriceLog setTime(Date time) {
        this.time = time;
        return this;
    }
}
