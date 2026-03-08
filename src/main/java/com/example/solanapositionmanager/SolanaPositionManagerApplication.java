package com.example.solanapositionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolanaPositionManagerApplication {

    public static void main(String[] args) {
        System.out.println("Solana Position manager");

        SpringApplication.run(SolanaPositionManagerApplication.class, args);
    }

}
