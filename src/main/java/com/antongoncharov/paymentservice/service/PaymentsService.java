package com.antongoncharov.paymentservice.service;

import com.antongoncharov.paymentservice.domain.Payment;
import com.antongoncharov.paymentservice.exception.NotFoundException;
import com.antongoncharov.paymentservice.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Service that handles Payment entity operations
 */
@Component
public class PaymentsService implements IPaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        payment.setExternalId(UUID.randomUUID().toString());
        return paymentsRepository.save(payment);
    }

    @Override
    public Page<Payment> getAllPayments(Pageable pageable) {
        return paymentsRepository.findAll(pageable);
    }

    @Override
    public Optional<Payment> getPaymentByExternalId(String id) {
        return paymentsRepository.findByExternalId(id);
    }

    @Override
    public Payment updatePayment(Payment toUpdate, String id) throws NotFoundException {
        Optional<Payment> before = paymentsRepository.findByExternalId(id);
        Payment payment = before.orElseThrow(NotFoundException::new);
        toUpdate.setId(payment.getId());
        toUpdate.setExternalId(payment.getExternalId());
        return paymentsRepository.save(toUpdate);
    }

    @Override
    public void deletePayment(String id) throws NotFoundException {
        Optional<Payment> toDelete = paymentsRepository.findByExternalId(id);
        Payment payment = toDelete.orElseThrow(NotFoundException::new);
        paymentsRepository.delete(payment);
    }

}
