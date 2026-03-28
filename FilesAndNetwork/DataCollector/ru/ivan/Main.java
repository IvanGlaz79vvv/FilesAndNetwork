package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        PrintCsv("data");
//        PrintJson("data");
//        PrintLinesOfLocalFile("html/Метро Москвы.html");
//        PrintLinesOfWEB("https://skillbox-java.github.io/");
//        PrintLinesWithStationsSortedLocal("html/Метро Москвы.html");
//        PrintLinesWithStationsSortedWEB("https://skillbox-java.github.io/");

//        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
//        recurringStations(moscowMetroStations.getLocalHtmlStations("html/Метро Москвы.html"));

        printGetObjectStation("html/Метро Москвы.html", "data");
    }


    public static void printGetObjectStation(String path, String data) {
        Map<String, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, data);

        for (String line : mapOfAllStations.keySet()) {
            System.out.println("=".repeat(120));
            System.out.println("\n" + line.toUpperCase());
            System.out.println("—".repeat(168));
//            System.out.printf("%-24s| %-32s| %12s| %7s| %-15s| %s%n","Станция", "Линия", "Дата", "Глубина", "Переход", "Наличие пересадки");
            System.out.printf("%-20s| %-20s| %10s| %6s| %-15s| %-5b%n", "Станция", "Линия", "Дата", "Глубина", "Переход", "Наличие пересадки");
            System.out.println("-".repeat(168));
            mapOfAllStations.get(line).forEach(station -> {
//                System.out.printf("%-24s| %-32s| %12s| %7.1f| %-15s| %b%n",
                System.out.printf("%-20s| %-20s| %10s| %7.1f| %-67s| %-5b%n",
                        station.getName(),
                        station.getLine(),
                        station.getDate(),
                        station.getDepth(),
                        station.getTransition(),
                        station.hasConnection);
            });
        }
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
    MoscowMetroStations.getLocalHtmlStations(path).entrySet().forEach(entry -> {
        System.out.println("\nsize: " + entry.getValue().size());
        System.out.println(/*"\n" +*/ entry.getKey());
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

public static void recurringStations(Map<String, List<Stations>> mapOfStations) {

    // Шаг 1: Создаем единый поток и сохраняем его в коллекцию
    List<Stations> allStations = mapOfStations.values().stream()
            .flatMap(List::stream)
            .toList(); // Сохраняем поток в коллекцию

    // Шаг 2: Поиск дубликатов
    List<Stations> hasConnectionList = new LinkedList<>();
    List<Stations> recurringList = new LinkedList<>();
    for (Stations station : allStations) {
        if (station.hasConnection) {
            hasConnectionList.add(station);
            if (station.getTransition().contains(station.getName().replaceAll("^\\d+\\.\\s+", ""))) {
                recurringList.add(station);
            }
        }
    }
    System.out.println("\n**********  hasConnectionList  **********");
    System.out.println("Size: " + hasConnectionList.size());
    hasConnectionList.forEach(station -> System.out.printf("%-29s" + "%-25s%n", station.getName() + ": ", station.getTransition()));
    System.out.println("\n**********  recurringList  **********");
    System.out.println("Size: " + recurringList.size());
    recurringList.forEach(station -> System.out.printf("%-29s" + "%-25s%n", station.getName() + ": ", station.getTransition()));
}
}