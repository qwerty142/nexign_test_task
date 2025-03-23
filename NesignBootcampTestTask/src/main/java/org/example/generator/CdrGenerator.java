package org.example.generator;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.repository.repositories.CdrRepository;
import org.example.repository.repositories.UserRepository;
import org.example.repository.tables.Cdr;
import org.example.repository.tables.User;

import java.util.*;

@AllArgsConstructor
public class CdrGenerator {
    private final CdrRepository cdrRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();

    @PostConstruct
    public void generateCdrRecords() {
        List<User> users = userRepository.findAll();
        if (users.size() < 10) {
            createDefaultUsers();
            users = userRepository.findAll();
        }

        List<Cdr> cdrList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        for (int i = 0; i < 1000; i++) {
            User incomeUser = users.get(random.nextInt(users.size()));
            User outcomeUser;
            do {
                outcomeUser = users.get(random.nextInt(users.size()));
            } while (incomeUser.equals(outcomeUser));

            int callType = random.nextInt(2) + 1;
            int callDuration = random.nextInt(600) + 30;

            Date beginCall = calendar.getTime();
            calendar.add(Calendar.SECOND, callDuration);
            Date endCall = calendar.getTime();

            Cdr cdr = Cdr.builder()
                    .call_type(callType)
                    .income_number(incomeUser)
                    .outcome_number(outcomeUser)
                    .begin_call(beginCall)
                    .end_call(endCall)
                    .build();

            cdrList.add(cdr);

            calendar.add(Calendar.MINUTE, random.nextInt(30) + 1);
        }

        cdrRepository.saveAll(cdrList);
    }

    private void createDefaultUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNumber("79" + (random.nextInt(900000000) + 100000000));
            user.setName("User" + (i + 1));
            users.add(user);
        }
        userRepository.saveAll(users);
    }

}
