package org.example.nesignbootcamptesttask;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.dto.CdrDto;
import org.example.repository.tables.Cdr;
import org.example.repository.tables.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestAPI {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGenerateUdrMonth() throws Exception {
        mockMvc.perform(get("/cdrs/udr/month?month=january&year=2024"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testFindCdrById() throws Exception {
        mockMvc.perform(get("/cdrs?cdrId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testUpdateCdr() throws Exception {

        User incomeUser = new User("1234567890", "Иван");
        User outcomeUser = new User("9876543210", "Анна");

        Cdr cdr1 = new Cdr(1L, 1, incomeUser, incomeUser, new Date(), new Date());

        String jsonRequest = objectMapper.writeValueAsString(cdr1);

        String jsonRequestUser = objectMapper.writeValueAsString(incomeUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestUser))
                .andExpect(status().isOk())
        ;


        mockMvc.perform(patch("/cdrs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
        ;
    }
}
