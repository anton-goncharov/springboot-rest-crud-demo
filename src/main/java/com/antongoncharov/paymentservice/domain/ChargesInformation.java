package com.antongoncharov.paymentservice.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "charges_information")
public class ChargesInformation {

    @Id
    @GeneratedValue
    private long id;

    private String bearerCode;
    private double receiverChargesAmount;
    private String receiverChargesCurrency;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "charges_info_id", referencedColumnName = "id")
    private List<Charge> senderCharges;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<Charge> getSenderCharges() {
        return senderCharges;
    }

    public void setSenderCharges(List<Charge> senderCharges) {
        this.senderCharges = senderCharges;
    }
}
