package ru.ivan.writer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.ivan.LineName;
import ru.ivan.Stations;
import ru.ivan.MoscowMetroStations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WriteToJson {

    public static void makeJSON(String path, String data) {
        // 1. Получаем исходную карту (Линия -> Список станций)
        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, data);

        // 2. Собираем все станции из всех линий в один общий список
        List<Stations> allStationsFlatList = new ArrayList<>();
        for (List<Stations> stationsOnLine : mapOfAllStations.values()) {
            allStationsFlatList.addAll(stationsOnLine);
        }
        // Или с помощью Stream API:
        // List<Stations> allStationsFlatList = mapOfAllStations.values().stream()
        //         .flatMap(List::stream)
        //         .collect(Collectors.toList());

        // 3. Создаем объект-контейнер, который будет преобразован в JSON
        StationContainer container = new StationContainer(allStationsFlatList);

        // 4. Настраиваем и используем Jackson для записи
        ObjectMapper mapper = new ObjectMapper();

        // Включаем красивое форматирование (отступы), чтобы JSON был читаемым
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Эта настройка говорит Jackson'у не включать поля со значением null
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            mapper.writeValue(new File("stations_array.json"), container);
            System.out.println("Файл успешно записан!");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
