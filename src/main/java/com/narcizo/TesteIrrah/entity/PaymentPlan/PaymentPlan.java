package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "plan_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "planType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrePaidPlan.class, name = "PREPAID"),
        @JsonSubTypes.Type(value = PostPaidPlan.class, name = "POSTPAID")
})
public abstract class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double basePlanBalance;
    private double basePlanLimit;
    @Column(name = "plan_type", insertable = false, updatable = false)
    private String planType;

    public PaymentPlan(double planBalance, double planLimit) {
        this.basePlanBalance = planBalance;
        this.basePlanLimit = planLimit;
    }

    public PaymentPlan() {
    }

    public Long getId() {
        return id;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public double getBasePlanBalance() {
        return basePlanBalance;
    }

    public void setBasePlanBalance(double basePlanBalance) {
        this.basePlanBalance = basePlanBalance;
    }

    public double getBasePlanLimit() {
        return basePlanLimit;
    }

    public void setBasePlanLimit(double basePlanLimit) {
        this.basePlanLimit = basePlanLimit;
    }

    public abstract double usePlan(double cost);
}