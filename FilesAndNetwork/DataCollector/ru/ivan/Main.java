package ru.ivan;

import org.jsoup.select.Elements;
import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parseSites.MoscowMetroStations;
import ru.ivan.parse_JSON_CSV.DatesCSV;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyParceCSV;
import ru.ivan.parse_JSON_CSV.MyParceJSON;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String pathLocal = "html/Метро Москвы.html";
        String pathWeb = "https://skillbox-java.github.io/";
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        MySearchFiles mySearchFiles = new MySearchFiles();
        MyParceCSV myParceCSV = new MyParceCSV();
        MyParceJSON myParceJSON = new MyParceJSON();
        List<String> dataAddress = mySearchFiles.searchMyFiles("data");

        /**Вывод CSV*/
        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
        List<String> pathOfCSV = dataAddress.stream()
                .filter(s -> s.endsWith(".csv"))
                .collect(Collectors.toList());
        for (String path : pathOfCSV) {
            System.out.println();
            System.out.println(path);
            List<DatesCSV> listOfCSV = myParceCSV.parceMyCSV(path);
            listOfCSV.forEach(csv -> System.out.println("\t" + csv));
        }

        /**Вывод JSON*/
        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
        List<String> pathOfJson = dataAddress.stream()
                .filter(s -> s.endsWith(".json"))
                .collect(Collectors.toList());
        for (String path : pathOfJson) {
            System.out.println("\n" + path + ":");
            List<Depths> listOfDepths = myParceJSON.parceMyJson(path);
            listOfDepths.forEach(item -> System.out.println("\t" + item));
        }

        /**Вывод линий с локального файла сайта */
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
        System.out.println("\nLOCAL Lines:");
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));

        /**Вывод линий с сайта он-лайн */
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта он-лайн");
        System.out.println("\nWEB Lines:");
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(pathWeb);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));

        /**Вывод списка всех станций отсортированный по линиям*/
        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций отсортированный по линиям");
        Map<String, Elements> map = new LinkedHashMap<>();
        map = moscowMetroStations.getLocalHtmlStations(pathLocal);
        for (String s : map.keySet()) {
            System.out.println("\n" + s + ":");
            map.get(s).forEach(l -> System.out.println("\t" + l.text()));
        }
    }
}