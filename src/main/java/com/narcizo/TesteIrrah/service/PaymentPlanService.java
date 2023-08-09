package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.entity.PaymentPlan.PostPaidPlan;
import com.narcizo.TesteIrrah.entity.PaymentPlan.PrePaidPlan;
import com.narcizo.TesteIrrah.repository.PaymentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentPlanService {
    @Autowired
    private PaymentPlanRepository repository;

    public List<PaymentPlan> getPaymentPlanList() {
        return repository.findAll();
    }

    public PaymentPlan getPaymentPlan(Long paymentPlanId){
        return this.checkIfPaymentPlanObjectExists(paymentPlanId);
    }

    public PaymentPlan createPaymentPlan(PaymentPlan paymentPlan) {
        if (Objects.equals(paymentPlan.getPlanType(), "PREPAID")) {
            PrePaidPlan prePlan = new PrePaidPlan(
                    paymentPlan.getBasePlanBalance()
            );
            prePlan.setPlanType(paymentPlan.getPlanType());

            if(this.isPaymentPlanValid(prePlan))
                return repository.save(prePlan);
            return new PrePaidPlan();
        }
        else if(Objects.equals(paymentPlan.getPlanType(), "POSTPAID")) {
            PostPaidPlan postPlan = new PostPaidPlan(
                    paymentPlan.getBasePlanBalance(), paymentPlan.getBasePlanLimit()
            );
            postPlan.setPlanType(paymentPlan.getPlanType());

            if(this.isPaymentPlanValid(postPlan))
                return repository.save(postPlan);
            return new PostPaidPlan();
        }

        return new PrePaidPlan();
    }

    public PaymentPlan updatePaymentPlan(Long paymentPlanId, PaymentPlan updatedPaymentPlan){
        PaymentPlan existingPaymentPlan = this.checkIfPaymentPlanObjectExists(paymentPlanId);

        existingPaymentPlan.setBasePlanBalance(updatedPaymentPlan.getBasePlanBalance());
        existingPaymentPlan.setBasePlanLimit(updatedPaymentPlan.getBasePlanLimit());
        existingPaymentPlan.setPlanType(updatedPaymentPlan.getPlanType());

        if(this.isPaymentPlanValid(existingPaymentPlan))
            return repository.save(existingPaymentPlan);

        if(Objects.equals(existingPaymentPlan.getPlanType(), "POSTPAID"))
            return new PostPaidPlan();

        return new PrePaidPlan();
    }

    public void deletePaymentPlan(Long paymentPlanId){
        PaymentPlan paymentPlan = this.checkIfPaymentPlanObjectExists(paymentPlanId);

        repository.delete(paymentPlan);
    }

    public PaymentPlan checkIfPaymentPlanObjectExists(Long paymentPlanId){
        return repository.findById(paymentPlanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "PaymentPlan not found with id: " +
                                paymentPlanId
                ));
    }

    private boolean isPaymentPlanValid(PaymentPlan paymentPlan){
        if (Objects.equals(paymentPlan.getPlanType(), "PREPAID"))
            return paymentPlan.getBasePlanBalance() > 0;
        else if (Objects.equals(paymentPlan.getPlanType(), "POSTPAID"))
            return paymentPlan.getBasePlanBalance() >= 0 && paymentPlan.getBasePlanLimit() > 0;

        return false;
    }
}
