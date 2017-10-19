package com.antongoncharov.paymentservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChargesInformationDTO {

    private String bearerCode;
    private double receiverChargesAmount;
    private String receiverChargesCurrency;
    private List<ChargeDTO> senderCharges;

    public String getBearerCode() {
        return bearerCode;
    }

    public void setBearerCode(String bearerCode) {
        this.bearerCode = bearerCode;
    }

    public double getReceiverChargesAmount() {
        return receiverChargesAmount;
    }

    public void setReceiverChargesAmount(double receiverChargesAmount) {
        this.receiverChargesAmount = receiverChargesAmount;
    }

    public String getReceiverChargesCurrency() {
        return receiverChargesCurrency;
    }

    public void setReceiverChargesCurrency(String receiverChargesCurrency) {
        this.receiverChargesCurrency = receiverChargesCurrency;
    }

    public List<ChargeDTO> getSenderCharges() {
        return senderCharges;
    }

    public void setSenderCharges(List<ChargeDTO> senderCharges) {
        this.senderCharges = senderCharges;
    }

    @Override
    public String toString() {
        return "ChargesInformationDTO{" +
                "bearerCode='" + bearerCode + '\'' +
                ", receiverChargesAmount='" + receiverChargesAmount + '\'' +
                ", receiverChargesCurrency='" + receiverChargesCurrency + '\'' +
                ", senderCharges=" + senderCharges +
                '}';
    }
}
