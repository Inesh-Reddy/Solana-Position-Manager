package com.example.solanapositionmanager.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LiquidityPositionDto {

    private String protocol;
    private String pair;
    private BigDecimal amountA;
    private BigDecimal amountB;
    private double feeTier;
    private double yourSharePercent;
    private BigDecimal earnedNet;
    private BigDecimal totalValue;

    public LiquidityPositionDto(
            String protocol,
            String pair,
            BigDecimal amountA,
            BigDecimal amountB,
            double feeTier,
            double yourSharePercent,
            BigDecimal earnedNet,
            BigDecimal totalValue) {

        this.protocol = protocol;
        this.pair = pair;
        this.amountA = amountA;
        this.amountB = amountB;
        this.feeTier = feeTier;
        this.yourSharePercent = yourSharePercent;
        this.earnedNet = earnedNet;
        this.totalValue = totalValue;
    }

}
