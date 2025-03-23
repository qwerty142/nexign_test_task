package org.example.repository.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cdrs")
public class Cdr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer call_type;

    @ManyToOne
    @JoinColumn(name = "number_income", nullable = false)
    private User income_number;

    @ManyToOne
    @JoinColumn(name = "number_outcome", nullable = false)
    private User outcome_number;

    private Date begin_call;

    private Date end_call;
}
