package ru.ivan.parse_JSON_CSV;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParseCSV {

    public static Map<String, String> parseCSV(String path) {
        Map<String, String> mapOfAllDates = new LinkedHashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",");
                if (fragments.length < 2) continue; // пропускаем строки с недостаточным количеством фрагментов
                String name = fragments[0];
                String date = fragments[1];
                mapOfAllDates.put(name, date);
            }
        } catch (Exception e) {
            System.out.println("Список пустой или неверный индекс.");
            e.printStackTrace();
        }
        return mapOfAllDates;
    }
}
