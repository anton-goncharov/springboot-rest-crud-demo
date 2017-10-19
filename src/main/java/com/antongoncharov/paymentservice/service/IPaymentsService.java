package com.antongoncharov.paymentservice.service;

import com.antongoncharov.paymentservice.domain.Payment;
import com.antongoncharov.paymentservice.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPaymentsService {
    Payment createPayment(Payment payment);

    Page<Payment> getAllPayments(Pageable pageable);

    Optional<Payment> getPaymentByExternalId(String id);

    Payment updatePayment(Payment toUpdate, String id) throws NotFoundException;

    void deletePayment(String id) throws NotFoundException;
}
