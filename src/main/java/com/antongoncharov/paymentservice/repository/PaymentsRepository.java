package com.antongoncharov.paymentservice.repository;

import com.antongoncharov.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentsRepository extends JpaRepository<Payment, Long> {

    public Optional<Payment> findByExternalId(String externalId);

}
