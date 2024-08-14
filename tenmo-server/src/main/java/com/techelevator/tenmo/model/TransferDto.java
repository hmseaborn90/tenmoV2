package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDto {
    private int tranferId;
    private String accountFrom;
    private String accountTo;
    private BigDecimal amount;

    public TransferDto(){

    }
    public TransferDto(int tranferId, String accountFrom, String accountTo, BigDecimal amount) {
        this.tranferId = tranferId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getTranferId() {
        return tranferId;
    }

    public void setTranferId(int tranferId) {
        this.tranferId = tranferId;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
