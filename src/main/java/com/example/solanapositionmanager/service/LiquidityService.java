package com.example.solanapositionmanager.service;

import com.example.solanapositionmanager.dto.LiquidityPositionDto;
import com.example.solanapositionmanager.model.LiquidityPosition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class LiquidityService {

    public LiquidityPosition createLiquidityPosition(
            String walletAddress,
            String protocol,
            String tokenA,
            String tokenB,
            BigDecimal amountA,
            BigDecimal amountB,
            double feeTier,
            double yourSharePercent) {

        Instant now = Instant.now();

        return new LiquidityPosition(
                walletAddress,
                protocol,
                tokenA,
                tokenB,
                amountA,
                amountB,
                feeTier,
                yourSharePercent,
                now
        );
    }

    public LiquidityPositionDto toDto(LiquidityPosition position) {
        BigDecimal earned = position.calculateEarnings();
        BigDecimal total = position.getTotalValue();

        String pair = position.getTokenA() + "-" + position.getTokenB();

        return new LiquidityPositionDto(
                position.getProtocol(),
                pair,
                position.getAmountA(),
                position.getAmountB(),
                position.getFeeTier(),
                position.getYourSharePercent(),
                earned,
                total
        );
    }
}
