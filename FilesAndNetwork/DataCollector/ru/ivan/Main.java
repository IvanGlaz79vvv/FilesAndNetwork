package ru.ivan;

import org.jsoup.nodes.Element;

import javax.lang.model.util.Elements;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String pathLocal = "html/Метро Москвы.html";
        String pathWeb = "https://skillbox-java.github.io/";
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();

//        System.out.println("\nLOCAL Lines:");
//        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
//        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));
//
//        System.out.println("\nWEB Lines:");
//        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(pathWeb);
//        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));

        Map<String, List<Element>> map = new LinkedHashMap<>();
        map = moscowMetroStations.getLocalHtmlStations(pathLocal);
        for (String s : map.keySet()) {
            System.out.println("\n" + s + ":");
            map.get(s).forEach(l-> System.out.println("\t" + l.text()));
        }
    }
}