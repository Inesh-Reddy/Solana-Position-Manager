package com.example.solanapositionmanager.service;

import com.example.solanapositionmanager.dto.LendingPositionDto;
import com.example.solanapositionmanager.model.LendingPosition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;

@Service
public class LendingService {

    private final Random random = new Random();

    private double getMockApy(String asset){
        if("USDC" .equalsIgnoreCase(asset)){
            return 0.03 + random.nextDouble() * 0.04;
        }
        if("SOL" .equalsIgnoreCase(asset)) {
            return 0.05 + random.nextDouble() * 0.06;
        }
        return 0.04;
    }

    public LendingPosition createLendingPosition(
            String walletAddress,
            String protocol,
            String assetSymbol,
            BigDecimal amount) throws IllegalAccessException {
        double apy = getMockApy(assetSymbol);
        Instant now = Instant.now();

        return new LendingPosition(walletAddress, protocol, assetSymbol, amount, now, apy);
    }

    public LendingPositionDto toDto(LendingPosition position){
        BigDecimal earned = position.calculateEarnings();
        BigDecimal total = position.getTotalValue();

        return new LendingPositionDto(
                position.getAssetSymbol(),
                position.getPrincipalAmount(),
                position.getApy(),
                earned,
                total
        );
    }

}
