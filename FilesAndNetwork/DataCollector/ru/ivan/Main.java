package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyDate;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.*;

public class Main {
    public static void main(String[] args) {

//        PrintCsv("data");
//        PrintJson("data");
//        PrintLinesOfLocalFile("html/Метро Москвы.html");
//        PrintLinesOfWEB("https://skillbox-java.github.io/");
//        PrintLinesWithStationsSortedLocal("html/Метро Москвы.html");
        PrintLinesWithStationsSortedWEB("https://skillbox-java.github.io/");

//        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
//        recurringStations(moscowMetroStations.getLocalHtmlStations("html/Метро Москвы.html"));

//        getObjectStation("html/Метро Москвы.html", "data");
    }


    public static /*Map<String, List<Stations>>*/ void getObjectStation(String path, String data) {
        String name = null;
        String line = null;
        String date = null;
        double depth = 0;
        String transition = null;
        boolean connection = false;
        List<Stations> listOfAllStations = new LinkedList<>();

        Map<String, List<Stations>> mapOfStations = MoscowMetroStations.getLocalHtmlStations(path);
        Map<String, List<MyDate>> mapOfDates = MyParseCSV.csvOutput(data);
        Map<String, List<Depths>> mapOfDepths = MyParceJSON.jsonOutput(data);

        List<Depths> allDepths = new ArrayList<>();
        Map<String, Double> mapOfAllDepths = new HashMap<>();
        List<MyDate> allDates = new ArrayList<>();
        Map<String, String> mapOfAllDates = new HashMap<>();

        for (List<Depths> depthsList : mapOfDepths.values()) {
            allDepths.addAll(depthsList);
        }
        for (Depths depths : allDepths) {
            mapOfAllDepths.put(depths.getStation_name(), depths.getDepth());
        }

        for (List<MyDate> myDateList : mapOfDates.values()) {
            allDates.addAll(myDateList);
        }
        for (MyDate myDates : allDates) {
            mapOfAllDates.put(myDates.getName(), myDates.getDate());
        }


        for (String lines : mapOfStations.keySet()) {
            line = lines.replaceAll("^[\\d\\w+]+\\.\\s+", "");
            for (Stations station : mapOfStations.get(lines)) {
                name = station.getName().replaceAll("^[\\d\\w+]+\\.\\s+", "");
                transition = station.getTransition();
                connection = station.hasConnection;
                if (mapOfAllDepths.get(name) != null) {
                    depth = mapOfAllDepths.get(name);
                }
                date = mapOfAllDates.get(name);
                Stations stationForList = new Stations(name, line, date, depth, transition, connection);
                listOfAllStations.add(stationForList);
            }
        }
        listOfAllStations.forEach(System.out::println);
//        System.out.println(name + "; " + line + "; " + date + "; " + depth + "; " + transition);
//        Stations station = new Stations(name, line, date, depth, transition);
//        System.out.println(station);
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
            System.out.println("\n" + entry.getKey());
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