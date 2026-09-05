package ru.ivan.parse_JSON_CSV;

import ru.ivan.MySearchFiles;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MyParseCSV {

    public static Map<String, List<MyDate>> csvOutputToMap(String data) {
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<MyDate> listOfMyDate = new LinkedList<>();
        Map<String, List<MyDate>> mapOfData = new LinkedHashMap<>();
        List<String> pathOfCsv = dataAddress.stream()
                .filter(s -> s.endsWith(".csv"))
                .toList();
        for (String path : pathOfCsv) {
            listOfMyDate = parseMyCSV(path);
            mapOfData.put(path, listOfMyDate);
        }
        return mapOfData;
    }


    public static List<MyDate> parseMyCSV(String path) {
        List<MyDate> myDate = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",");
                String name = fragments[0];
                String date = fragments[1];
                MyDate datesTMP = new MyDate(name, date);
                myDate.add(datesTMP);
            }
        } catch (Exception e) {
            System.out.println("Список пустой или неверный индекс.");
            e.printStackTrace();
        }
        return myDate;
    }
}
