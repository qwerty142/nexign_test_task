package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class UdrDTO {

    private String number;

    private long totalIncome;

    private long totalOutcome;

}
