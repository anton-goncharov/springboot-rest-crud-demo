package com.antongoncharov.paymentservice.test;

import com.antongoncharov.paymentservice.domain.Payment;
import com.antongoncharov.paymentservice.domain.PaymentAttributes;
import com.antongoncharov.paymentservice.dto.PaymentAttributesDTO;
import com.antongoncharov.paymentservice.dto.PaymentDTO;
import com.antongoncharov.paymentservice.dto.mapping.PaymentMapper;
import org.junit.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

public class MappingTest {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Test
    public void shouldMapPaymentToPaymentDto() {
        Payment payment = new Payment();
        PaymentAttributes paymentAttributes = new PaymentAttributes();
        paymentAttributes.setAmount(2.200001);
        paymentAttributes.setProcessingDate(LocalDate.of(2017,2,23));
        paymentAttributes.setCurrency("USD");
        payment.setAttributes(paymentAttributes);

        PaymentDTO paymentDTO = PaymentMapper.INSTANCE.paymentToPaymentDto(payment);

        assertThat(paymentDTO).isNotNull();
        assertThat(paymentDTO.getAttributes()).isNotNull();
        assertThat(paymentDTO.getAttributes().getCurrency()).isEqualTo("USD");
        assertThat(paymentDTO.getAttributes().getAmount()).isEqualTo(2.200001);
        assertThat(paymentDTO.getAttributes().getProcessingDate()).isEqualTo("2017-02-23");
    }

    @Test
    public void shouldMapPaymentDtoToPayment() {
        PaymentDTO paymentDTO = new PaymentDTO();
        PaymentAttributesDTO paymentAttributesDTO = new PaymentAttributesDTO();
        paymentAttributesDTO.setAmount(2.200001);
        paymentAttributesDTO.setCurrency("USD");
        paymentAttributesDTO.setProcessingDate("2017-02-23");
        paymentDTO.setAttributes(paymentAttributesDTO);

        Payment payment = PaymentMapper.INSTANCE.paymentDtoToPayment(paymentDTO);

        assertThat(payment).isNotNull();
        assertThat(payment.getAttributes()).isNotNull();
        assertThat(payment.getAttributes().getCurrency()).isEqualTo("USD");
        assertThat(payment.getAttributes().getAmount()).isEqualTo(2.200001);
        assertThat(payment.getAttributes().getProcessingDate()).isEqualTo(LocalDate.of(2017,2,23));
    }

}
