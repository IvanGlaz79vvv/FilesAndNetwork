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


        /**Вывод CSV*/
        List<String> dataAddress = mySearchFiles.serchMyFiles("data");
//        String pathOfCSV = String.valueOf(dataAddress.get(0));
        List<String> pathOfCSV = dataAddress.stream()
                .filter(s -> s.endsWith(".csv"))
                .collect(Collectors.toList());
//        List<DatesCSV> listOfCSV = myParceCSV.parceMyCSV(pathOfCSV);
        for (String path : pathOfCSV) {
            System.out.println();
            List<DatesCSV> listOfCSV = myParceCSV.parceMyCSV(path);
            listOfCSV.forEach(csv -> System.out.println(path + csv));
        }
//        dataAddress.forEach(System.out::println);

        /**Вывод JSON*/
        List<String> jsonAddress = mySearchFiles.serchMyFiles("data");
//        String pathOfJson = String.valueOf(jsonAddress.get(1));
        List<String> pathOfJson = dataAddress.stream()
                .filter(s -> s.endsWith(".json"))
                .collect(Collectors.toList());
        for(String path:pathOfJson) {
            System.out.println();
            List<Depths> listOfDepths = myParceJSON.parceMyJson(path);
            listOfDepths.forEach(System.out::println);
//        jsonAddress.forEach(System.out::println);
        }

        /**Вывод линий с локального файла сайта */
        System.out.println("\nLOCAL Lines:");
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));

        /**Вывод линий с сайта он-лайн */
        System.out.println("\nWEB Lines:");
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(pathWeb);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));

        /**Вывод списка всех станций отсортированный по линиям*/
        Map<String, Elements> map = new LinkedHashMap<>();
        map = moscowMetroStations.getLocalHtmlStations(pathLocal);
        for (String s : map.keySet()) {
            System.out.println("\n" + s + ":");
            map.get(s).forEach(l -> System.out.println("\t" + l.text()));
        }
    }
}