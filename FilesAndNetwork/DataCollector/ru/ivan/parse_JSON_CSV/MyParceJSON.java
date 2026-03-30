package ru.ivan.parse_JSON_CSV;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.ivan.MySearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MyParceJSON<T> {

    public static Map<String, List<Depths>> jsonOutput(String data) {
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<Depths> listOfDepths = new LinkedList<>();
        Map<String, List<Depths>> mapOfDepths = new LinkedHashMap<>();
        List<String> pathOfJson = dataAddress.stream()
                .filter(s -> s.endsWith(".json"))
                .collect(Collectors.toList());
        for (String path : pathOfJson) {
            listOfDepths = parseMyJson(path);
            mapOfDepths.put(path, listOfDepths);
        }
        return mapOfDepths;
    }

    public static List<Depths> parseMyJson(String path) {
        List<Depths> list = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(getDataFromJSON(path));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // 1. Получаем имя станции
                String name = (String) jsonObject.get("station_name");
                if (name == null || name.trim().isEmpty()) {
                    name = "Безымянная станция";
                }

                // 2. Получаем глубину
                Depths depthsEntry = null; // Создаем переменную для объекта
                Object depthObj = jsonObject.get("depth");

                if (depthObj == null) {
                    // Создаем объект с текстом ошибки
                    depthsEntry = new Depths(name, null);
                } else if (depthObj instanceof Number) {
                    double depthValue = ((Number) depthObj).doubleValue();
                    depthsEntry = new Depths(name, depthValue); // Обычный объект
                } else if (depthObj instanceof String) {
                    String depthStr = ((String) depthObj).trim();

                    if (depthStr.isEmpty() || "?".equals(depthStr)) {
                        depthsEntry =  new Depths(name, null);
                    } else {
                        try {
                            double depthValue = Double.parseDouble(depthStr.replace(',', '.'));
                            depthsEntry = new Depths(name, depthValue);
                        } catch (NumberFormatException e) {
                            depthsEntry =  new Depths(name, null);
                        }
                    }
                } else {
                    depthsEntry =  new Depths(name, null);
                }

                // 3. Добавляем объект в список (он уже содержит либо данные, либо ошибку)
                list.add(depthsEntry);
            }

        } catch (ParseException e) {
            throw new RuntimeException("Ошибка парсинга JSON-файла: " + e.getMessage(), e);
        }
        return list;
    }


    static private String getDataFromJSON(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(builder::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}