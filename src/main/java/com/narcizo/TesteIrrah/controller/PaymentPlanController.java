package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.service.PaymentPlanService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-plan")
public class PaymentPlanController {
    @Autowired
    private PaymentPlanService service;

    @GetMapping
    public List<PaymentPlan> getPaymentPlanList(){
        return service.getPaymentPlanList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentPlan> getPaymentPlan(@PathVariable Long id){
        PaymentPlan paymentPlan = service.getPaymentPlan(id);

        return ResponseEntity.ok(paymentPlan);
    }

    @PostMapping
    public ResponseEntity<PaymentPlan> createPaymentPlan(@RequestBody PaymentPlan paymentPlan) {
        PaymentPlan updated = service.createPaymentPlan(paymentPlan);
        return ResponseEntity.ok(updated);
    }

    @PutMapping
    public ResponseEntity<PaymentPlan> updatePaymentPlan(@PathVariable Long id, @RequestBody PaymentPlan paymentPlan){
        PaymentPlan updated = service.updatePaymentPlan(id, paymentPlan);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentPlan(@PathVariable Long id){
        service.deletePaymentPlan(id);
    }
}
