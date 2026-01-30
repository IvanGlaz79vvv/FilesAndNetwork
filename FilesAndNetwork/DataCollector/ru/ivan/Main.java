package ru.ivan;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String pathLocal = "html/Метро Москвы.html";
        String pathWeb = "https://skillbox-java.github.io/";
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();

        System.out.println("\nLOCAL Lines:");
        Map<String, String> mapLocal = moscowMetroLines.GetlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));
//
        System.out.println("\nWEB Lines:");
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(pathWeb);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));
    }
}
