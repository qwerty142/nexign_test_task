package org.example.nesignbootcamptesttask;

import org.example.repository.repositories.CdrRepository;
import org.example.repository.repositories.UserRepository;
import org.example.repository.tables.Cdr;
import org.example.repository.tables.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IntegrationTestDb {
    @Autowired
    private CdrRepository _cdrRepository;

    @Autowired
    private UserRepository _userRepository;

    @Test
    public void testSaveAndRetrieveCdr() {
        // Создаем тестовые записи
        User incomeUser = new User("1234567890", "Иван");
        User outcomeUser = new User("9876543210", "Анна");

        _userRepository.save(incomeUser);
        _userRepository.save(outcomeUser);

        Cdr cdr = Cdr.builder()
                .end_call(new Date())
                .begin_call(new Date())
                .income_number(incomeUser)
                .outcome_number(outcomeUser)
                .call_type(1)
                .build();

        _cdrRepository.save(cdr);

        // Проверяем, что запись успешно сохранилась
        List<Cdr> result = _cdrRepository.findAll();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getIncome_number().getNumber()).isEqualTo("1234567890");
    }
}
