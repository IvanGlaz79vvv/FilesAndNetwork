package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class MoscowMetroLinesAndStations {
    String lineName;
    int lineNum;
    String stations;

    WebParce webParce = new WebParce();

    public Map<String,String> getStations(String path){
        Map<String, String> mapOfStations = new LinkedHashMap<>();
        Document doc = webParce.parceSite(path);
        Elements lines = doc.select("div.t-metrostation-list-table");
        lines.forEach(line -> {
            String lineName = line.text();
            String lineNumber = line.attr("data-line");
            mapOfStations.put(lineNumber, lineName);
        });
        return mapOfStations;
    }


    public Map<String, String> getLines(String path) {
        Map<String, String> mapOfLines = new LinkedHashMap<>();
        Document doc = webParce.parceSite(path);
        Elements lines = doc.select("span.t-metrostation-list-header");
        lines.forEach(line -> {
            String lineName = line.text();
            String lineNumber = line.attr("data-line");
            mapOfLines.put(lineNumber, lineName);
        });
        return mapOfLines;
    }
}
