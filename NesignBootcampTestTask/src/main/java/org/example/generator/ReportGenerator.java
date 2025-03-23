package org.example.generator;

import org.example.controller.dto.CdrDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class ReportGenerator {
    public static String saveCdrToFile(List<CdrDto> cdrList) {
        // Создаем уникальное имя файла
        String fileName = "reports/" + UUID.randomUUID() + ".csv";

        // Создаем папку reports, если ее нет
        try {
            Files.createDirectories(Paths.get("reports"));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании папки reports", e);
        }

        // Записываем данные в файл
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            // Заголовок
            writer.write("ID;CallType;IncomeNumber;OutcomeNumber;BeginCall;EndCall\n");

            // Запись данных
            for (CdrDto cdr : cdrList) {
                writer.write(cdr.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи файла", e);
        }

        return fileName; // Возвращаем путь к файлу
    }
}
