package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.repository.PaymentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.save(paymentPlan);
    }

    public PaymentPlan updatePaymentPlan(Long paymentPlanId, PaymentPlan updatedPaymentPlan){
        PaymentPlan existingPaymentPlan = this.checkIfPaymentPlanObjectExists(paymentPlanId);

        existingPaymentPlan.setPlanBalance(updatedPaymentPlan.getPlanBalance());
        existingPaymentPlan.setPlanLimit(updatedPaymentPlan.getPlanLimit());
        existingPaymentPlan.setPlanType(updatedPaymentPlan.getPlanType());

        return repository.save(existingPaymentPlan);
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
}
