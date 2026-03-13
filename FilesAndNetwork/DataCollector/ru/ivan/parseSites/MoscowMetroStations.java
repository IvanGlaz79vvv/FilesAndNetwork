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
//            System.out.println("lineName:" + lineName);

            // Идентификатор линии (номер)
            String lineNum = header.attr("data-line");
//            System.out.println("lineNum: " + lineNum);

            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");

            List<String> stations = new ArrayList<>(stationsElements.size());
            for(Element element:stationsElements){
                stations.add(element.text());
            }

            //Вывод переходов
            Elements transitions = stationsElements.select("span.t-icon-metroln");
            for(Element transition: transitions){
                System.out.println("transitions: " + transition.attr("title"));
            }

            mapOfLinesAndStations.put(lineNum + ". " + lineName, stations);
        }
        return mapOfLinesAndStations;
    }
}
