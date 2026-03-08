package com.example.solanapositionmanager.model;

import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class LendingPosition extends Position {
    private double apy;

    public LendingPosition(String walletAddress, String protocol, String assetSymbol, BigDecimal depositedAmount, Instant depositTime, double apy) throws IllegalAccessException {
        super(walletAddress, protocol, assetSymbol, depositedAmount, depositTime);
        if(depositedAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalAccessException("Amount must be more than zero.");
        }
        if(apy < 0){
            throw new IllegalAccessException("APY cannot be negative");
        }

        this.apy = apy;
    }


    @Override
    public BigDecimal calculateEarnings() {
        double days = getDaysHeld();
        if(days <= 0){
            return BigDecimal.ZERO;
        }

        BigDecimal ratePerDay = BigDecimal.valueOf(apy).divide(BigDecimal.valueOf(100), 8, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(365), 8, BigDecimal.ROUND_HALF_UP);
        BigDecimal earnings = getPrincipalAmount().multiply(ratePerDay).multiply(BigDecimal.valueOf(days));

        return earnings.setScale(6, BigDecimal.ROUND_HALF_UP);
    }

}
