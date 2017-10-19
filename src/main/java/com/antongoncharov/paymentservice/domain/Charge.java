package com.antongoncharov.paymentservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "charge")
public class Charge {

    @Id
    @GeneratedValue
    private long id;

    private double amount;
    private String currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charges_info_id")
    private ChargesInformation parent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ChargesInformation getParent() {
        return parent;
    }

    public void setParent(ChargesInformation parent) {
        this.parent = parent;
    }
}
