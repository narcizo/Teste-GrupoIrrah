package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREPAID")
@JsonTypeName("PREPAID")
public class PrePaidPlan extends PaymentPlan {
    Long id;

    public PrePaidPlan(double balance) {
        super.setBasePlanBalance(balance);
    }

    public PrePaidPlan() {
        setPlanType("PREPAID");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public double usePlan(double cost) {
        if (getBasePlanBalance() >= cost) {
            double newBalance = getBasePlanBalance() - cost;
            setBasePlanBalance(newBalance);
            return newBalance;
        } else {
            return -1;
        }
    }
}