package com.example.solanapositionmanager.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class LiquidityPosition extends Position {

    private String tokenA;
    private String tokenB;
    private BigDecimal amountA;
    private BigDecimal amountB;
    private double feeTier;
    private double yourSharePercent;

    public LiquidityPosition(
            String walletAddress,
            String protocol,
            String tokenA,
            String tokenB,
            BigDecimal amountA,
            BigDecimal amountB,
            double feeTier,
            double yourSharePercent,
            Instant depositTime) {

        super(walletAddress, protocol, tokenA + "-" + tokenB, amountA.add(amountB), depositTime);

        if (amountA.compareTo(BigDecimal.ZERO) <= 0 || amountB.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Both token amounts must be positive");
        }
        if (feeTier < 0 || feeTier > 0.05) {
            throw new IllegalArgumentException("Fee tier should be between 0 and 5%");
        }
        if (yourSharePercent < 0 || yourSharePercent > 100) {
            throw new IllegalArgumentException("Share percent must be between 0 and 100");
        }

        this.tokenA = tokenA.toUpperCase();
        this.tokenB = tokenB.toUpperCase();
        this.amountA = amountA;
        this.amountB = amountB;
        this.feeTier = feeTier;
        this.yourSharePercent = yourSharePercent;

    }

    @Override
    public BigDecimal calculateEarnings() {

        double days = getDaysHeld();
        if (days <= 0) {
            return BigDecimal.ZERO;
        }

        // In real life this comes from API (Birdeye, Dexscreener, etc.)
        double dailyPoolVolumeUsd = 100000 + (Math.random() * 900000);

        BigDecimal dailyFees = BigDecimal.valueOf(dailyPoolVolumeUsd)
                .multiply(BigDecimal.valueOf(feeTier))
                .multiply(BigDecimal.valueOf(yourSharePercent / 100.0));

        BigDecimal totalFees = dailyFees.multiply(BigDecimal.valueOf(days));

        // Step 2: Mock rewards (e.g. protocol tokens like RAY, ORCA)
        BigDecimal rewards = totalFees.multiply(BigDecimal.valueOf(0.10));

        // Step 3: Very simple Impermanent Loss simulation
        double priceChangePercent = -30 + (Math.random() * 60);

        double ilPenaltyPercent = Math.abs(priceChangePercent) * 0.05;
        if (ilPenaltyPercent > 15) ilPenaltyPercent = 15;

        BigDecimal ilLoss = getPrincipalAmount()
                .multiply(BigDecimal.valueOf(ilPenaltyPercent / 100.0));

        // Final earnings = fees + rewards - IL loss
        BigDecimal netEarnings = totalFees
                .add(rewards)
                .subtract(ilLoss);

        if (netEarnings.compareTo(BigDecimal.ZERO) < 0) {
            netEarnings = BigDecimal.ZERO;
        }

        return netEarnings.setScale(6, BigDecimal.ROUND_HALF_UP);
    }
}
