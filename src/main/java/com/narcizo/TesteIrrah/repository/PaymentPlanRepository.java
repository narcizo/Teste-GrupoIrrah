package com.narcizo.TesteIrrah.repository;

import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {
}