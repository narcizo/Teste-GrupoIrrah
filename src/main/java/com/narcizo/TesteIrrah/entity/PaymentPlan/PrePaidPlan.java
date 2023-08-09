package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREPAID")
@JsonTypeName("PREPAID")
public class PrePaidPlan extends PaymentPlan {
    private double planLimit;
    private double balance;

    public PrePaidPlan(double planLimit, double balance) {
        this.planLimit = planLimit;
        this.balance = balance;
    }

    public PrePaidPlan() {
    }

    public double getPlanLimit() {
        return planLimit;
    }

    public void setPlanLimit(double planLimit) {
        this.planLimit = planLimit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void usePlan(double cost) {
        if (balance + cost <= planLimit) {
            balance += cost;
        } else {
            // TODO Handle exceeded limit
        }
    }
}