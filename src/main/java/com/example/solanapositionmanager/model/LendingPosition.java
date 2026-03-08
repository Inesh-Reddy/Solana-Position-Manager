package com.example.solanapositionmanager.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LendingPosition extends Position {
    private Long depositedAmount;
    private double apy;

    public LendingPosition(){};

}
