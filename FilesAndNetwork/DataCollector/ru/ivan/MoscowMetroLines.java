package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoscowMetroLines {
    public Map<String, String> getWebHtmlLines(String path) {
        Map<String, String> mapLines = new LinkedHashMap<>();
        {
            WebHtmlParce webHtmlParce = new WebHtmlParce();
            Document doc = webHtmlParce.parceWebHtml(path);
            Elements lines = doc.select(".js-metro-line");
            if (!lines.isEmpty()) {
                for (Element l : lines) {
                    String lName = l.text();
                    String lNumber = l.attr("data-line");
                    mapLines.put(lNumber, lName);
                }
            }
        }
        return mapLines;
    }

    public Map<String, String> getlocalHtmlLines(String path) {
        Map<String, String> mapLines = new LinkedHashMap<>();
        {
            LocalHtmlParce localHtmlParce = new LocalHtmlParce();
            Document doc = localHtmlParce.parceLocalHtml(path);
            Elements lines = doc.select(".js-metro-line");
            if (!lines.isEmpty()) {
                for (Element l : lines) {
                    String lName = l.text();
                    String lNumber = l.attr("data-line");
                    mapLines.put(lNumber, lName);

                }
            }
        }
        return mapLines;
    }
}

//span.t-metrostation-list-header
//div.t-metrostation-list-table
//        Elements lines = doc.select("span.t-metrostation-list-header");
//        Elements stations = doc.select("span.name");


/**
 * public Map<String, String> getStations(String path) {
 * Map<String, String> mapOfStations = new LinkedHashMap<>();
 * Document doc = webHtmlParce.parceSite(path);
 * //t-metrostation-list-header
 * //"div.t-metrostation-list-table"
 * Elements lines = doc.select("div.t-metrostation-list-table");
 * //        String num = doc.select("div.t-metrostation-list-table").attr("data-line");
 * <p>
 * <p>
 * Elements allLinesNames = doc.select("span.t-metrostation-list-header");
 * Elements allLinesNumbers = doc.select("span.t-metrostation-list-header");
 * <p>
 * Elements stations = doc.select("span.name");
 * Elements numbers = doc.select("span.num");
 * <p>
 * //        System.out.println(Arrays.asList(allLines.text()));
 * //        allLinesNumbers.forEach(n-> System.out.print(n.text() + "\n"));
 * //        allLinesNames.forEach(l-> System.out.println(l.text()));
 * //        stations.forEach(l-> System.out.println(l.text()));
 * //        numbers.forEach(System.out::println);
 * //        System.out.println(num);
 * <p>
 * //        System.out.println(lines.text());
 * <p>
 * for (int i = 0; i < numbers.size(); i++) {
 * Element n = numbers.get(i);
 * Element s = stations.get(i);
 * station = n.text() + " " + s.text();
 * stationNames.add(station);
 * }
 * lines.forEach(line -> {
 * String stationNames = stations.text();
 * String lineNumber = line.attr("data-line");
 * String stationWithNumber = numbers.text() + stations.text();
 * mapOfStations.put(lineNumber, stationNames);
 * });
 * return mapOfStations;
 * }
 * <p>
 * public Map<String, String> getLines(String path) {
 * Map<String, String> mapOfLines = new LinkedHashMap<>();
 * Document doc = webHtmlParce.parceSite(path);
 * //div.t-metrostation-list-table
 * //span.t-metrostation-list-header
 * Elements lines = doc.select("div.t-metrostation-list-table");
 * lines.forEach(line -> {
 * String lineName = line.text();
 * System.out.println(lineName);
 * //            String lineNumber = line.attr("data-line");
 * //            mapOfLines.put(lineNumber, lineName);
 * });
 * return mapOfLines;
 * }
 * }
 */