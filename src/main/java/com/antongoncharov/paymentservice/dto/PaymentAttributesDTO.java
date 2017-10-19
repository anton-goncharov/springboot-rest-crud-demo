package com.antongoncharov.paymentservice.dto;

import com.antongoncharov.paymentservice.domain.ChargesInformation;
import com.antongoncharov.paymentservice.domain.ForeignExchange;
import com.antongoncharov.paymentservice.domain.Party;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentAttributesDTO {

    @NotNull
    private double amount;

    private String currency;
    private String endToEndReference;
    private String numericReference;
    private long paymentId;
    private String paymentPurpose; // possibly enum?
    private String paymentType;
    private String reference;
    private String schemePaymentType;
    private String schemePaymentSubType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String processingDate;

    private ForeignExchangeDTO fx;
    private ChargesInformationDTO chargesInformation;

    private PartyDTO beneficiaryParty;
    private PartyDTO debtorParty;
    private PartyDTO sponsorParty;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEndToEndReference() {
        return endToEndReference;
    }

    public void setEndToEndReference(String endToEndReference) {
        this.endToEndReference = endToEndReference;
    }

    public String getNumericReference() {
        return numericReference;
    }

    public void setNumericReference(String numericReference) {
        this.numericReference = numericReference;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSchemePaymentType() {
        return schemePaymentType;
    }

    public void setSchemePaymentType(String schemePaymentType) {
        this.schemePaymentType = schemePaymentType;
    }

    public String getSchemePaymentSubType() {
        return schemePaymentSubType;
    }

    public void setSchemePaymentSubType(String schemePaymentSubType) {
        this.schemePaymentSubType = schemePaymentSubType;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public ForeignExchangeDTO getFx() {
        return fx;
    }

    public void setFx(ForeignExchangeDTO fx) {
        this.fx = fx;
    }

    public ChargesInformationDTO getChargesInformation() {
        return chargesInformation;
    }

    public void setChargesInformation(ChargesInformationDTO chargesInformation) {
        this.chargesInformation = chargesInformation;
    }

    public PartyDTO getBeneficiaryParty() {
        return beneficiaryParty;
    }

    public void setBeneficiaryParty(PartyDTO beneficiaryParty) {
        this.beneficiaryParty = beneficiaryParty;
    }

    public PartyDTO getDebtorParty() {
        return debtorParty;
    }

    public void setDebtorParty(PartyDTO debtorParty) {
        this.debtorParty = debtorParty;
    }

    public PartyDTO getSponsorParty() {
        return sponsorParty;
    }

    public void setSponsorParty(PartyDTO sponsorParty) {
        this.sponsorParty = sponsorParty;
    }

    @Override
    public String toString() {
        return "PaymentAttributesDTO{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", endToEndReference='" + endToEndReference + '\'' +
                ", numericReference='" + numericReference + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", paymentPurpose='" + paymentPurpose + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", reference='" + reference + '\'' +
                ", schemePaymentType='" + schemePaymentType + '\'' +
                ", schemePaymentSubType='" + schemePaymentSubType + '\'' +
                ", processingDate='" + processingDate + '\'' +
                ", fx=" + fx +
                ", chargesInformation=" + chargesInformation +
                ", beneficiaryParty=" + beneficiaryParty +
                ", debtorParty=" + debtorParty +
                ", sponsorParty=" + sponsorParty +
                '}';
    }
}
