package com.antongoncharov.paymentservice.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "payment_attributes")
public class PaymentAttributes {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private double amount;

    private String currency;
    private String endToEndReference;
    private String numericReference;
    private long paymentId;
    private String paymentPurpose;
    private String paymentType;
    private String reference;
    private String schemePaymentType;
    private String schemePaymentSubType;
    private LocalDate processingDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="foreign_exchange_id")
    private ForeignExchange fx;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="charges_info_id")
    private ChargesInformation chargesInformation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="beneficiary_party_id")
    private Party beneficiaryParty;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="debtor_party_id")
    private Party debtorParty;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="sponsor_party_id")
    private Party sponsorParty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public ForeignExchange getFx() {
        return fx;
    }

    public void setFx(ForeignExchange fx) {
        this.fx = fx;
    }

    public ChargesInformation getChargesInformation() {
        return chargesInformation;
    }

    public void setChargesInformation(ChargesInformation chargesInformation) {
        this.chargesInformation = chargesInformation;
    }

    public Party getBeneficiaryParty() {
        return beneficiaryParty;
    }

    public void setBeneficiaryParty(Party beneficiaryParty) {
        this.beneficiaryParty = beneficiaryParty;
    }

    public Party getDebtorParty() {
        return debtorParty;
    }

    public void setDebtorParty(Party debtorParty) {
        this.debtorParty = debtorParty;
    }

    public Party getSponsorParty() {
        return sponsorParty;
    }

    public void setSponsorParty(Party sponsorParty) {
        this.sponsorParty = sponsorParty;
    }
}
