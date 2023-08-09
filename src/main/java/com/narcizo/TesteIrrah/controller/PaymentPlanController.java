package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.service.PaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment-plan")
public class PaymentPlanController {
    @Autowired
    private PaymentPlanService service;

    @PostMapping
    public PaymentPlan createPaymentPlan(@RequestBody PaymentPlan paymentPlan) {
        System.out.println("Payment Post");
        return service.savePaymentPlan(paymentPlan);
    }

}
