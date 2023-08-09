package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREPAID")
@JsonTypeName("PREPAID")
public class PrePaidPlan extends PaymentPlan {
    private double planLimit;
    private double planBalance;

    public PrePaidPlan(double planLimit, double balance) {
        this.planLimit = planLimit;
        this.planBalance = balance;
    }

    public PrePaidPlan() {
    }

    public double getPlanLimit() {
        return planLimit;
    }

    public void setPlanLimit(double planLimit) {
        this.planLimit = planLimit;
    }

    public double getPlanBalance() {
        return planBalance;
    }

    public void setPlanBalance(double planBalance) {
        this.planBalance = planBalance;
    }

    @Override
    public void usePlan(double cost) {
        if (planBalance + cost <= planLimit) {
            planBalance += cost;
        } else {
            // TODO Handle exceeded limit
        }
    }
}