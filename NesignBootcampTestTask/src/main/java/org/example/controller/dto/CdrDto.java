package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.repository.tables.User;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CdrDto {
    private Long id;

    private Integer call_type;

    private User income_number;

    private User outcome_number;

    private Date begin;

    private Date end;
}
