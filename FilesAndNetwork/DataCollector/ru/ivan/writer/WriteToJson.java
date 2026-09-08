package ru.ivan.writer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import ru.ivan.DTO.LineName;
import ru.ivan.DTO.Stations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WriteToJson {

    public static void makeJsonFromMap(Map<LineName, List<Stations>> mapOfAllStations, String nameOfJson) {
        // 1. Получаем карту: Линия -> Список станций mapOfAllStations

        // 2. Собираем все станции в один плоский список
        List<Stations> allStationsFlatList = new ArrayList<>();
        for (List<Stations> stationsOnLine : mapOfAllStations.values()) {
            allStationsFlatList.addAll(stationsOnLine);
        }

        log.debug("Подготовка к записи файла: {}, количество станций: {}", nameOfJson, allStationsFlatList.size());

        // --- ЭТО ВАЖНО: делаем обёртку ---
        Map<String, List<Stations>> wrapper = new LinkedHashMap<>();
        wrapper.put("stations", allStationsFlatList);
        // ---------------------------------

        // 3. Настраиваем ObjectMapper ДО записи
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 4. Пишем в файл только один раз
        try {
            String name = nameOfJson + ".json";
            mapper.writeValue(new File(name), wrapper);
            log.info("Файл " + nameOfJson + " успешно записан!");
        } catch (IOException e) {
            log.error("Ошибка записи в файл: {}", nameOfJson, e);
            e.printStackTrace();
        }
    }

    public static void makeMapJson(Map<LineName, List<Stations>> mapOfAllStations, String nameOfJson) {
        Map<String, List<String>> linesWithStationNames = new LinkedHashMap<>();

        for (Map.Entry<LineName, List<Stations>> entry : mapOfAllStations.entrySet()) {
            LineName line = entry.getKey();
            List<String> names = entry.getValue().stream()
                    .map(Stations::getName)
                    .toList();
            linesWithStationNames.put(line.getId(), names);
        }

        int totalStations = linesWithStationNames.values().stream()
                .mapToInt(List::size)
                .sum();
        log.debug("Подготовка к записи файла: {}, количество записей (линия -> список имён): {}, суммарное число станций: {}", nameOfJson, linesWithStationNames.size(), totalStations);

        Map<String, Map<String, List<String>>> wrapper = new LinkedHashMap<>();
        wrapper.put("stations", linesWithStationNames);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String name = nameOfJson + ".json";
            mapper.writeValue(new File(name), wrapper);
            log.info("Файл " + nameOfJson + " успешно записан!");
        } catch (IOException e) {
            log.error("Ошибка записи в файл: {}", nameOfJson, e);
            e.printStackTrace();

        }
    }
}

