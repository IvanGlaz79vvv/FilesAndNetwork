//package ru.ivan.writer;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import ru.ivan.Stations;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//public class JsonComplexWriter {
//    public static void main(String[] args) {
//        Map<String, List<Stations>> mapOfAllStations = Stations.getObjectStation("html/Метро Москвы.html", "data");
//
//        // --- Блок 1: Сбор данных для "stations" ---
//        Map<String, List<String>> stationsMap = new LinkedHashMap<>();
//
//        mapOfAllStations.forEach((lineName, stationsList) -> {
//            // Извлекаем номер линии (например, "1", "2", "D2")
//            String lineNumber = lineName.replaceAll("^([A-Z0-9]+).*", "$1");
//
//            // Преобразуем список объектов Stations в список имен (String)
//            List<String> stationNames = stationsList.stream()
//                    .map(Stations::getName)
//                    .toList(); // или .collect(Collectors.toList());
//
//            stationsMap.put(lineNumber, stationNames);
//        });
//
//        // --- Блок 2: Сбор данных для "lines" ---
//        // Здесь мы предполагаем, что знаем названия и цвета линий.
//        // В реальном проекте эти данные нужно брать из ваших объектов Stations.
//        List<LineInfo> linesList = new ArrayList<>();
//        linesList.add(new LineInfo(1, "Кировско-Выборгская", "red"));
//        linesList.add(new LineInfo(2, "Московско-Петроградская", "blue"));
//        linesList.add(new LineInfo(3, "Невско-Василеостровская", "green"));
//        linesList.add(new LineInfo(4, "Правобережная", "orange"));
//        linesList.add(new LineInfo(5, "Фрунзенско-Приморская", "violet"));
//
//        // --- Блок 3: Сбор данных для "connections" ---
//        // Это самый сложный блок. Данные о пересадках обычно не хранятся в объекте Станция.
//        // Их нужно прописать вручную или извлечь из другой структуры данных.
//        // Здесь мы создаем их вручную для примера.
//
//        List<List<ConnectionStation>> connectionsList = new ArrayList<>();
//
//        // Пересадка 1: Невский проспект (2) <-> Гостиный двор (3)
//        List<ConnectionStation> transfer1 = Arrays.asList(
//                new ConnectionStation(2, "Невский проспект"),
//                new ConnectionStation(3, "Гостиный двор")
//        );
//
//        // Пересадка 2: Площадь Восстания (1) <-> Маяковская (3)
//        List<ConnectionStation> transfer2 = Arrays.asList(
//                new ConnectionStation(1, "Площадь Восстания"),
//                new ConnectionStation(3, "Маяковская")
//        );
//
//        // Тройная пересадка: Сенная Площадь (2) <-> Спасская (4) <-> Садовая (5)
//        List<ConnectionStation> transfer3 = Arrays.asList(
//                new ConnectionStation(2, "Сенная Площадь"),
//                new ConnectionStation(4, "Спасская"),
//                new ConnectionStation(5, "Садовая")
//        );
//
//        connectionsList.add(transfer1);
//        connectionsList.add(transfer2);
//        connectionsList.add(transfer3);
//
//        // --- Финальный шаг: Создание контейнера и запись ---
//        MetroData metroData = new MetroData(stationsMap, connectionsList, linesList);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        try {
//            mapper.writeValue(new File("metro_complex.json"), metroData);
//            System.out.println("Сложный JSON файл успешно создан!");
//        } catch (IOException e) {
//            System.err.println("Ошибка записи: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}