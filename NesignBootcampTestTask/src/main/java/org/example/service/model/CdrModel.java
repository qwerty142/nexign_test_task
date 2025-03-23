package org.example.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.repository.tables.User;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CdrModel {
    private Long id;

    private Integer call_type;

    private User income_number;

    private User outcome_number;

    private Date begin;

    private Date end;
}
