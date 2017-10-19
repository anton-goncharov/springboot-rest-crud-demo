package com.antongoncharov.paymentservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ForeignExchangeDTO {

    private String contractReference;
    private double exchangeRate;
    private double originalAmount;
    private String originalCurrency;

    public String getContractReference() {
        return contractReference;
    }

    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    @Override
    public String toString() {
        return "ForeignExchangeDTO{" +
                "contractReference='" + contractReference + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", originalAmount='" + originalAmount + '\'' +
                ", originalCurrency='" + originalCurrency + '\'' +
                '}';
    }
}
