package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parseSites.MoscowMetroStations;
import ru.ivan.parse_JSON_CSV.DatesCSV;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;
import ru.ivan.writer.WriteToJson;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

//        csvOutput("data");
//        jsonOutput("data");
//        linesOfLocalFile("html/Метро Москвы.html");
//        linesOfWEB("https://skillbox-java.github.io/");
        linesWithStationsSortedLocal("html/Метро Москвы.html");
        linesWithStationsSortedWEB("https://skillbox-java.github.io/");

//        WriteToJson writeToJson = new WriteToJson();
//        writeToJson.makesAnEntryInJSON("html/Метро Москвы.html");
    }

    public static void csvOutput(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
        MyParseCSV myParseCSV = new MyParseCSV();
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<String> pathOfCSV = dataAddress.stream()
                .filter(s -> s.endsWith(".csv"))
                .collect(Collectors.toList());
        for (String path : pathOfCSV) {
            System.out.println();
            System.out.println(path);
            List<DatesCSV> listOfCSV = myParseCSV.parceMyCSV(path);
            listOfCSV.forEach(csv -> System.out.println("\t" + csv));
        }
    }

    public static void jsonOutput(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
        MyParceJSON myParceJSON = new MyParceJSON();
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<String> pathOfJson = dataAddress.stream()
                .filter(s -> s.endsWith(".json"))
                .collect(Collectors.toList());
        for (String path : pathOfJson) {
            System.out.println("\n" + path + ":");
            List<Depths> listOfDepths = myParceJSON.parceMyJson(path);
            listOfDepths.forEach(item -> System.out.println("\t" + item));
        }
    }

    public static void linesOfLocalFile(String pathLocal) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
        System.out.println("\nLOCAL Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void linesOfWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта онлайн");
        System.out.println("\nWEB Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(path);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void linesWithStationsSortedLocal(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с локального файла отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        Map<String, List<Stations>> map = moscowMetroStations.getLocalHtmlStations("html/Метро Москвы.html");
        for(String line:map.keySet()){
            System.out.println("\n" + line);
            map.get(line).forEach(System.out::println);
        }
    }

    public static void linesWithStationsSortedWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с сайта отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        Map<String, List<Stations>> map = moscowMetroStations.getWebHtmlStations(path);
        for(String line:map.keySet()){
            System.out.println("\n" + line);
            map.get(line).forEach(System.out::println);
        }
    }
}