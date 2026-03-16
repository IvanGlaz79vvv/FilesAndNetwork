package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyDate;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        PrintCsv("data");
        PrintJson("data");
        PrintLinesOfLocalFile("html/Метро Москвы.html");
        PrintLinesWithStationsSortedLocal("html/Метро Москвы.html");
        PrintLinesOfWEB("https://skillbox-java.github.io/");
        PrintLinesWithStationsSortedWEB("https://skillbox-java.github.io/");

//        WriteToJson writeToJson = new WriteToJson();
//        writeToJson.makesAnEntryInJSON("html/Метро Москвы.html");
    }

    public static void PrintCsv(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
        Map<String, List<MyDate>> mapOfCsvOutput = MyParseCSV.csvOutput("data");
        mapOfCsvOutput = MyParseCSV.csvOutput("data");
        for (String file : mapOfCsvOutput.keySet()) {
            System.out.println("\n" + file);
            for (MyDate myDate : mapOfCsvOutput.get(file)) {
                System.out.println("\t" + myDate);
            }
        }
    }

    public static void PrintJson(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
        Map<String, List<Depths>> mapOfJsonOutput = MyParceJSON.jsonOutput("data");
        mapOfJsonOutput = MyParceJSON.jsonOutput("data");
        for (String file : mapOfJsonOutput.keySet()) {
            System.out.println("\n" + file);
            for (Depths depths : mapOfJsonOutput.get(file)) {
                System.out.println("\t" + depths);
            }
        }
    }

    public static void PrintLinesOfLocalFile(String pathLocal) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
        System.out.println("\nLOCAL Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void PrintLinesOfWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта онлайн");
        System.out.println("\nWEB Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(path);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void PrintLinesWithStationsSortedLocal(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с локального файла отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        Map<String, List<Stations>> map = moscowMetroStations.getLocalHtmlStations("html/Метро Москвы.html");
        for (String line : map.keySet()) {
            System.out.println("\n" + line);
            map.get(line).forEach(station -> System.out.println("\t" + station));
        }
    }

    public static void PrintLinesWithStationsSortedWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с сайта отсортированный по линиям");
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        Map<String, List<Stations>> map = moscowMetroStations.getWebHtmlStations(path);
        for (String line : map.keySet()) {
            System.out.println("\n" + line);
            map.get(line).forEach(station -> System.out.println("\t" + station));
        }
    }
}