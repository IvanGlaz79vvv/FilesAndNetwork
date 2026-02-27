package ru.ivan;

import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parseSites.MoscowMetroStations;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyParceJSON;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathLocal = "html/Метро Москвы.html";
        String pathWeb = "https://skillbox-java.github.io/";
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        MyParceJSON myParceJSON = new MyParceJSON();
        MySearchFiles mySearchFiles = new MySearchFiles();

        /**Вывод JSON*/
        List<String> dataAddress = mySearchFiles.serchMyFiles("data");
        String pathOfJson = String.valueOf(dataAddress.get(1));
        List<Depths> listOfDepths = myParceJSON.parceMyJson(pathOfJson);
        listOfDepths.forEach(System.out::println);
        System.out.println();
        dataAddress.forEach(System.out::println);

        /**Вывод линий с локального файла сайта */
        /*System.out.println("\nLOCAL Lines:");
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));*/

        /**Вывод линий с сайта он-лайн */
        /*System.out.println("\nWEB Lines:");
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(pathWeb);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));*/

        /**Вывод списка всех станций отсортированный по линиям*/
        /*Map<String, Elements> map = new LinkedHashMap<>();
        map = moscowMetroStations.getLocalHtmlStations(pathLocal);
        for (String s : map.keySet()) {
            System.out.println("\n" + s + ":");
            map.get(s).forEach(l -> System.out.println("\t" + l.text()));
        }*/
    }
}