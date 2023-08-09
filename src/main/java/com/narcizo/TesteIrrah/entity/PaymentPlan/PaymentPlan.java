package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "plan_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "planType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrePaidPlan.class, name = "PREPAID"),
        @JsonSubTypes.Type(value = PostPaidPlan.class, name = "POSTPAID")
})
public abstract class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double planBalance;
    private double planLimit;

    public PaymentPlan(double planBalance, double planLimit) {
        this.planBalance = planBalance;
        this.planLimit = planLimit;
    }

    public PaymentPlan() {
    }

    public double getPlanBalance() {
        return planBalance;
    }

    public void setPlanBalance(double planBalance) {
        this.planBalance = planBalance;
    }

    public double getPlanLimit() {
        return planLimit;
    }

    public void setPlanLimit(double planLimit) {
        this.planLimit = planLimit;
    }

    public abstract void usePlan(double cost);
}