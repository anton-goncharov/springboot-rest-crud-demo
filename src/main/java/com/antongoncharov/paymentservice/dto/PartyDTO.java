package com.antongoncharov.paymentservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PartyDTO {

    private String accountName;
    private String accountNumber;
    private String accountNumber_code;
    private String accountType;
    private String address;
    private long bankId;
    private String bankIdCode;
    private String name;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber_code() {
        return accountNumber_code;
    }

    public void setAccountNumber_code(String accountNumber_code) {
        this.accountNumber_code = accountNumber_code;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBankIdCode() {
        return bankIdCode;
    }

    public void setBankIdCode(String bankIdCode) {
        this.bankIdCode = bankIdCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PartyDTO{" +
                "accountName='" + accountName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountNumber_code='" + accountNumber_code + '\'' +
                ", accountType='" + accountType + '\'' +
                ", address='" + address + '\'' +
                ", bankId='" + bankId + '\'' +
                ", bankIdCode='" + bankIdCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
