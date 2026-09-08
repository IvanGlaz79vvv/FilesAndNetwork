package ru.ivan.parse_JSON_CSV;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParceJSON {

    public static Map<String, Integer> parseJson(String path) {
        Map<String, Integer> map = new LinkedHashMap<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(getDataFromJSON(path));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                String name = (String) jsonObject.get("station_name");
                if (name == null || name.trim().isEmpty()) {
                    name = "Безымянная станция";
                }

                Object depthObj = jsonObject.get("depth");
                Integer depthValue = null;

                if (depthObj instanceof Number) {
                    depthValue = ((Number) depthObj).intValue();
                } else if (depthObj instanceof String) {
                    String depthStr = ((String) depthObj).trim();
                    if (!depthStr.isEmpty() && !"?".equals(depthStr)) {
                        try {
                            depthValue = (int) Double.parseDouble(depthStr.replace(',', '.'));
                        } catch (NumberFormatException ignored) {
                            // оставляем null
                        }
                    }
                }
                // если не Number и не String — depthValue остаётся null

                map.put(name, depthValue);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Ошибка парсинга JSON-файла: " + e.getMessage(), e);
        }
        return map;
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