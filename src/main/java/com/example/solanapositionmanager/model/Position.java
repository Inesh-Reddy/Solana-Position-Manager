package com.example.solanapositionmanager.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public abstract class Position {

    private String id;
    private String walletAddress;
    private String protocol;
    private String assetSymbol;
    private BigDecimal principalAmount;
    private Instant depositTime;

    public Position(String walletAddress, String protocol, Instant depositTime) {
        this.walletAddress = walletAddress;
        this.protocol = protocol;
        this.depositTime = depositTime;
    }

    public Position(String walletAddress, String protocol, String assetSymbol, BigDecimal principalAmount, Instant depositTime) {

        this.walletAddress = walletAddress;
        this.protocol = protocol;
        this.assetSymbol = assetSymbol;
        this.principalAmount = principalAmount;
        this.depositTime = depositTime;
    }

    public abstract BigDecimal calculateEarnings();

    protected double getDaysHeld(){
        if(depositTime == null){
            return  0.0;
        }
        return Instant.now().getEpochSecond() - depositTime.getEpochSecond();
    }

    public BigDecimal getTotalValue(){
        return principalAmount.add(calculateEarnings());
    }

}
