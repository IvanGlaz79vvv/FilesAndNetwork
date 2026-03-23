package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

//        PrintCsv("data");
//        PrintJson("data");
//        PrintLinesOfLocalFile("html/Метро Москвы.html");
//        PrintLinesOfWEB("https://skillbox-java.github.io/");
//        PrintLinesWithStationsSortedLocal("html/Метро Москвы.html");
        PrintLinesWithStationsSortedWEB("https://skillbox-java.github.io/");

//        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
//        duplicateStations(moscowMetroStations.getLocalHtmlStations("html/Метро Москвы.html"));
    }


    public static void PrintCsv(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
        MyParseCSV.csvOutput(path).entrySet().stream().forEach(entry -> {
            System.out.println("\nsize: " + entry.getValue().size());
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.println("\t" + value));
        });
    }

    public static void PrintJson(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
        MyParceJSON.jsonOutput(path).entrySet().forEach(entry -> {
            System.out.println("\nsize: " + entry.getValue().size());
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.println("\t" + value));
        });
    }

    public static void PrintLinesOfLocalFile(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
        System.out.println("\nLOCAL Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        moscowMetroLines.getlocalHtmlLines(path).forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void PrintLinesOfWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта онлайн");
        System.out.println("\nWEB Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        moscowMetroLines.getWebHtmlLines(path).forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void PrintLinesWithStationsSortedLocal(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с локального файла отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        moscowMetroStations.getLocalHtmlStations(path).entrySet().forEach(entry -> {
            System.out.println("\nsize: " + entry.getValue().size());
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.println("\t" + value));
        });
    }

    public static void PrintLinesWithStationsSortedWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с сайта отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        moscowMetroStations.getWebHtmlStations(path).entrySet().forEach(entry -> {
            System.out.println("\nsize: " + entry.getValue().size());
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.println("\t" + value));
        });
    }


    /**     Поиск повторов     */
    public static void duplicateStations(Map<String, List<Stations>> mapOfStations) {

        // Шаг 1: Создаем единый поток и сохраняем его в коллекцию
        List<Stations> allStations = mapOfStations.values().stream()
                .flatMap(List::stream)
                .toList(); // Сохраняем поток в коллекцию

        allStations.forEach(station-> System.out.println(station.name+ ": " + station.transition + " - " +station.transition.contains(station.name.replaceAll("^\\d+\\.\\s*", ""))));

        // Шаг 2: Используем сохраненную коллекцию для группировки
        Map<String, List<Stations>> groupedByName = allStations.stream()
                .collect(Collectors.groupingBy(Stations::getName));

        // Шаг 3: Поиск дубликатов
        LinkedList<Stations> globalDuplicates = groupedByName.values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(LinkedList::new));

        // Шаг 4: Вывод результата
        if (globalDuplicates.isEmpty()) {
            System.out.println("Глобальных совпадений названий станций не найдено.");
        } else {
            System.out.println("Найдены станции с повторяющимися названиями (всего " + globalDuplicates.size() + "):");

            // Уникальные названия выводим без повторов
            Set<String> uniqueNames = globalDuplicates.stream()
                    .map(Stations::getName)
                    .collect(Collectors.toSet());

            uniqueNames.forEach(name -> System.out.println(" - " + name));
        }
    }
}