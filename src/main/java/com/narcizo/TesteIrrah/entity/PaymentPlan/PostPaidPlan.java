package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POSTPAID")
@JsonTypeName("POSTPAID")
public class PostPaidPlan extends PaymentPlan {
    private double planBalance;

    public PostPaidPlan(double balance) {
        this.planBalance = balance;
    }

    public PostPaidPlan() {
    }

    public double getPlanBalance() {
        return planBalance;
    }

    public void setPlanBalance(double planBalance) {
        this.planBalance = planBalance;
    }

    @Override
    public void usePlan(double cost) {
        if (planBalance >= cost) {
            planBalance -= cost;
        } else {
            // TODO Handle insufficient balance
        }
    }
}