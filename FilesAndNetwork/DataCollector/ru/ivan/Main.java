package ru.ivan;

import org.jsoup.nodes.Document;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        WebParce webParce = new WebParce();
        String path = "https://skillbox-java.github.io/";
        Document htmlFile = webParce.parceSite(path);

        MoscowMetroLinesAndStations moscowMetroLinesAndStations = new MoscowMetroLinesAndStations();

        Map<String, String> mapOfMoscowMetroLines = moscowMetroLinesAndStations.getLines(path);
        mapOfMoscowMetroLines.forEach((key, value)-> System.out.println(key + ". " + value));

        Map<String, String> mapOfMoscowMetroStations = moscowMetroLinesAndStations.getStations(path);
        mapOfMoscowMetroStations.forEach((key, value)-> System.out.println("Линия " + key + ": " + value));
    }
}
