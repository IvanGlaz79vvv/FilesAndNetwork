package ru.ivan.parseSites;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class MoscowMetroStations {

    public Map<String, List<String>> getWebHtmlStations(String path) {
        WebHtmlParce webHtmlParce = new WebHtmlParce();
        Document doc = webHtmlParce.parceWebHtml(path);

        // Карта для хранения данных (ключ — название линии, значение — список станций)
        Map<String, List<String>> mapOfLinesAndStations = new LinkedHashMap<>();

        // Получаем все заголовки линий
        Elements linesHeaders = doc.select(".js-metro-line");
        List<String> listOfLines = new LinkedList<>();

        // Обрабатываем каждую линию
        for (Element header : linesHeaders) {
            // Имя линии
            String lineName = header.text();

            // Идентификатор линии (номер)
            String lineDataAttr = header.attr("data-line");

            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineDataAttr + "'] p.single-station");
//            String stationNum = Stations.attr("num");

            List<String> stations = new ArrayList<>(stationsElements.size());
            for(Element element:stationsElements){
                stations.add(element.text());
            }

            mapOfLinesAndStations.put(lineDataAttr + ". " + lineName, stations);
        }

        return mapOfLinesAndStations;
    }

    public Map<String, List<String>> getLocalHtmlStations(String path) {
        LocalHtmlParce localHtmlParce = new LocalHtmlParce();
        Document doc = localHtmlParce.parceLocalHtml(path);

        // Карта для хранения данных (ключ — название линии, значение — список станций)
        Map<String, List<String>> mapOfLinesAndStations = new LinkedHashMap<>();

        // Получаем все заголовки линий
        Elements linesHeaders = doc.select(".js-metro-line");
        List<String> listOfLines = new LinkedList<>();

        // Обрабатываем каждую линию
        for (Element header : linesHeaders) {
            // Имя линии
            String lineName = header.text();

            // Идентификатор линии (номер)
            String lineDataAttr = header.attr("data-line");

            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineDataAttr + "'] p.single-station");
//            String stationNum = Stations.attr("num");

            List<String> stations = new ArrayList<>(stationsElements.size());
            for(Element element:stationsElements){
                stations.add(element.text());
            }

            mapOfLinesAndStations.put(lineDataAttr + ". " + lineName, stations);
        }
        return mapOfLinesAndStations;
    }
}


/*
public Map<String, String> getWebHtmlStations(String path) {
    Map<String, String> mapStations = new LinkedHashMap<>();
    {
        WebHtmlParce webHtmlParce = new WebHtmlParce();
        Document doc = webHtmlParce.parceWebHtml(path);
        Elements stations = doc.select("div.t-metrostation-list-table");
        Elements numbers = doc.select("span.num");
        Elements names = doc.select("span.name");
        for (int i = 0; i < numbers.size(); i++) {
            mapStations.put(numbers.get(i).text(), names.get(i).text());
        }
    }
    return mapStations;
}*/
