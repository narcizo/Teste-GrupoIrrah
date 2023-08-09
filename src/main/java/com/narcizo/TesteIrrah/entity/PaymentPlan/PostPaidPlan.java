package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POSTPAID")
@JsonTypeName("POSTPAID")
public class PostPaidPlan extends PaymentPlan {
    private double balance;

    // Constructors, getters, setters


    public PostPaidPlan(double balance) {
        this.balance = balance;
    }

    public PostPaidPlan() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void usePlan(double cost) {
//        if (balance >= cost) {
//            balance -= cost;
//        } else {
//            // TODO Handle insufficient balance
//        }
    }
}