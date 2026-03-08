package com.example.solanapositionmanager.dto;


import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class LendingPositionDto {

    private String assetSymbol;
    private BigDecimal depositAmount;
    private double apy;
    private BigDecimal earnedInterest;
    private BigDecimal totalValue;

    public LendingPositionDto(String assetSymbol, BigDecimal depositAmount, double apy, BigDecimal earnedInterest, BigDecimal totalValue) {
        this.assetSymbol = assetSymbol;
        this.depositAmount = depositAmount;
        this.apy = apy;
        this.earnedInterest = earnedInterest;
        this.totalValue = totalValue;
    }

}
