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
    private double balance;
    private double planLimit;

    public abstract void usePlan(double cost);
}