package com.example.solanapositionmanager.controller;

import com.example.solanapositionmanager.dto.LendingPositionDto;
import com.example.solanapositionmanager.dto.LiquidityPositionDto;
import com.example.solanapositionmanager.model.LendingPosition;
import com.example.solanapositionmanager.model.LiquidityPosition;
import com.example.solanapositionmanager.service.LendingService;
import com.example.solanapositionmanager.service.LiquidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class PositionController {

    @Autowired
    private LendingService lendingService;
    @Autowired
    private LiquidityService liquidityService;

    @GetMapping("/lending/simulate")
    public ResponseEntity<LendingPositionDto> simulateLending(
            @RequestParam String wallet,
            @RequestParam String asset,
            @RequestParam String amount) throws IllegalAccessException {

        BigDecimal depositAmount = new BigDecimal(amount);

        LendingPosition position = lendingService.createLendingPosition(
                wallet,
                "Kamino Lend",
                asset.toUpperCase(),
                depositAmount
        );
        LendingPositionDto dto = lendingService.toDto(position);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/liquidity/simulate")
    public ResponseEntity<LiquidityPositionDto> simulateLiquidity(
            @RequestParam String wallet,
            @RequestParam String protocol,
            @RequestParam String tokenA,
            @RequestParam String tokenB,
            @RequestParam String amountA,
            @RequestParam String amountB,
            @RequestParam double feeTier,
            @RequestParam double sharePercent) {

        BigDecimal amtA = new BigDecimal(amountA);
        BigDecimal amtB = new BigDecimal(amountB);

        LiquidityPosition position = liquidityService.createLiquidityPosition(
                wallet,
                protocol,
                tokenA.toUpperCase(),
                tokenB.toUpperCase(),
                amtA,
                amtB,
                feeTier,
                sharePercent
        );

        LiquidityPositionDto dto = liquidityService.toDto(position);

        return ResponseEntity.ok(dto);
    }
}
