package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.repository.PaymentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentPlanService {
    @Autowired
    private PaymentPlanRepository paymentPlanRepository;

    public PaymentPlan savePaymentPlan(PaymentPlan paymentPlan) {

        return paymentPlanRepository.save(paymentPlan);
    }
}
