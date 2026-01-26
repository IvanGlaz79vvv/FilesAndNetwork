package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class MoscowMetroLines {
    String lineName;
    int lineNum;
    WebParce webParce = new WebParce();

    public Map<String, String> getLines(String path) {
        Map<String, String> map = new LinkedHashMap<>();
        Document doc = webParce.parceSite(path);
        Elements lines = doc.select("span.t-metrostation-list-header");
        lines.forEach(line -> {
            String lineName = line.text();
            String lineNumber = line.attr("data-line");
            map.put(lineNumber, lineName);
        });
        return map;
    }
}
