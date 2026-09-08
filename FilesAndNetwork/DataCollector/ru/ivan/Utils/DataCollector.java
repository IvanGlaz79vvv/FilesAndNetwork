package ru.ivan.Utils;

import ru.ivan.parse_JSON_CSV.ParceJSON;
import ru.ivan.parse_JSON_CSV.ParseCSV;
import ru.ivan.searcher.SearchFiles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataCollector {

    public static Map<String, Integer> collectDepths(String path, String extention) {
        SearchFiles searchFiles = new SearchFiles();
        Map<String, List<String>> files = searchFiles.searchFiles(path, extention);
        Map<String, Integer> allDepths = new LinkedHashMap<>();

//        for (String filePath : files.getOrDefault("json", List.of())) {
        for (String filePath : files.getOrDefault(extention, List.of())) {
            // каждый файл парсим отдельно и сливаем в общую карту
            allDepths.putAll(ParceJSON.parseJson(filePath));
        }
        return allDepths;
    }

    public static Map<String, String> collectDates(String path, String extention){
        SearchFiles searchFiles = new SearchFiles();
        Map<String, List<String>> files = searchFiles.searchFiles(path, extention);
        Map<String, String> allDates = new LinkedHashMap<>();

        for (String filePath : files.getOrDefault(extention, List.of())) {
            // каждый файл парсим отдельно и сливаем в общую карту
            allDates.putAll(ParseCSV.parseCSV(filePath));
        }
        return allDates;
    }
}
