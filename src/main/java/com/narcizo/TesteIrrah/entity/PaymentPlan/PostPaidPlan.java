package com.narcizo.TesteIrrah.entity.PaymentPlan;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POSTPAID")
@JsonTypeName("POSTPAID")
public class PostPaidPlan extends PaymentPlan {
    Long id;

    public PostPaidPlan(double balance, double planLimit) {
        super.setBasePlanBalance(balance);
        super.setBasePlanLimit(planLimit);
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

    @Override
    public void usePlan(double cost) {
        if (getBasePlanBalance() + cost <= getBasePlanLimit()) {
            setBasePlanBalance(getBasePlanBalance() + cost);
        } else {
            // TODO Handle exceeded limit
        }
    }
}