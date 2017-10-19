package com.antongoncharov.paymentservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentDTO {

    private String id;
    @NotNull
    private String organisationId;
    private String type;
    @NotNull
    private long version;
    @NotNull
    private PaymentAttributesDTO attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public PaymentAttributesDTO getAttributes() {
        return attributes;
    }

    public void setAttributes(PaymentAttributesDTO attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id='" + id + '\'' +
                ", organisationId='" + organisationId + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
