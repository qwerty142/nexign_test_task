package org.example.repository.repositories;

import org.example.repository.tables.Cdr;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@ComponentScan
public interface CdrRepository extends JpaRepository<Cdr, Long> {

    @Query("SELECT c FROM Cdr c WHERE (c.income_number.number = :number OR c.outcome_number.number = :number)" +
            "AND c.begin_call BETWEEN :startDate AND CURRENT_TIMESTAMP" +
            " ORDER BY c.begin_call DESC")
    List<Cdr> findAllByNumber(@Param("number") String number, @Param("startDate") Date startDate);

    @Query("SELECT c FROM Cdr c " +
            "WHERE (c.income_number.number = :number OR c.outcome_number.number = :number) " +
            "AND c.begin_call BETWEEN :startDate AND :endDate " +
            "ORDER BY c.begin_call DESC")
    List<Cdr> findAllByNumberAndPeriod(@Param("number") String number,
                                       @Param("startDate") Date startDate,
                                       @Param("endDate") Date endDate);

    @Query("SELECT c FROM Cdr c " +
            "WHERE FUNCTION('YEAR', c.begin_call) = :year " +
            "AND FUNCTION('MONTH', c.begin_call) = :month " +
            "ORDER BY c.begin_call DESC")
    List<Cdr> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
