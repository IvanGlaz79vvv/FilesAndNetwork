package ru.ivan.parse_JSON_CSV;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyParseCSV {

    public List<DatesCSV> parceMyCSV(String path) {
        List<DatesCSV> datesCSV = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",");
                String name = fragments[0];
                String date = fragments[1];
                DatesCSV datesTMP = new DatesCSV(name, date);
                datesCSV.add(datesTMP);
            }
        } catch (Exception e) {
            System.out.println("Список пустой или неверный индекс.");
            e.printStackTrace();
        }
        return datesCSV;
    }
}
