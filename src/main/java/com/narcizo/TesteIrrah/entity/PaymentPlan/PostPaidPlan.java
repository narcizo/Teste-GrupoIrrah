package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POSTPAID")
@JsonTypeName("POSTPAID")
public class PostPaidPlan extends PaymentPlan {
    private Long id;
    private double planBalance;

    public PostPaidPlan(Long id, double balance, String planType) {
        this.id = id;
        this.planBalance = balance;
    }

    public PostPaidPlan() {
        setPlanType("POSTPAID");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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