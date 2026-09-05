//package ru.ivan.writer;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import ru.ivan.LineName;
//import ru.ivan.Stations;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class WriteToJson {
//
//    public static void makeJSON(String path, String data) {
//        // 1. Получаем карту: Линия -> Список станций
//        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, data);
//
//        // 2. Собираем все станции в один плоский список
//        List<Stations> allStationsFlatList = new ArrayList<>();
//        for (List<Stations> stationsOnLine : mapOfAllStations.values()) {
//            allStationsFlatList.addAll(stationsOnLine);
//        }
//
//        // 3. Настраиваем ObjectMapper ДО записи
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        // 4. Пишем в файл только один раз
//        try {
//            mapper.writeValue(new File("stations_array.json"), allStationsFlatList);
//            System.out.println("Файл успешно записан!");
//        } catch (IOException e) {
//            System.err.println("Ошибка записи в файл: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
