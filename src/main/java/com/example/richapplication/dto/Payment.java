package com.example.richapplication.dto;

public class Payment {
    private Integer id;
    private Double amount;

    public Payment() {
    }

    public Payment(Integer id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
