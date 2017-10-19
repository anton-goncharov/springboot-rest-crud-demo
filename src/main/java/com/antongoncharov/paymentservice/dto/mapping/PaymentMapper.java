package com.antongoncharov.paymentservice.dto.mapping;

import com.antongoncharov.paymentservice.domain.Payment;
import com.antongoncharov.paymentservice.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "externalId", target = "id")
    PaymentDTO paymentToPaymentDto(Payment payment);

    @Mapping(source = "id", target = "externalId")
    Payment paymentDtoToPayment(PaymentDTO paymentDTO);

}
