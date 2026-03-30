package ru.ivan;

import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.ivan.parseSites.LocalHtmlParce;
import ru.ivan.parseSites.WebHtmlParce;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class MoscowMetroStations {
    private List<Stations> hasConnectionList = new LinkedList<>();
    private List<Stations> recurringList = new LinkedList<>();
    private Map<String, List<Stations>> mapOfConnectionAndRecurring = new LinkedHashMap<String, List<Stations>>();

    public static Map<String, List<Stations>> getWebHtmlStations(String path) {
        WebHtmlParce webHtmlParce = new WebHtmlParce();
        Document doc = webHtmlParce.parceWebHtml(path);
        Map<String, List<Stations>> mapOfLinesAndStations = new LinkedHashMap<>();

        // Получаем все заголовки линий
        Elements linesHeaders = doc.select(".js-metro-line");

        // Обрабатываем каждую линию
        for (Element header : linesHeaders) {
            List<Stations> listOfStations = new LinkedList<>();
            // Идентификатор линии (номер)
            String lineNum = header.attr("data-line");
            // Имя линии
            String name = header.text();
            String lineName = lineNum + ". " + name;

            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");
            for (Element element : stationsElements) {
                //Название станции
                String stationName = element.text();
                // Поиск перехода
                String transition = element.select("span.t-icon-metroln").attr("title");
                // Определяем наличие перехода
                boolean hasConnection = transition != null && !transition.isEmpty();
                // Создаем объект станции В ЛЮБОМ СЛУЧАЕ
                Stations station = new Stations(stationName, transition, hasConnection);
                // Добавляем станцию в список текущей линии
                listOfStations.add(station);
            }
            // Добавляем готовый список станций в карту ПОСЛЕ завершения цикла
            mapOfLinesAndStations.put(lineName, listOfStations);
        }
        return mapOfLinesAndStations;
    }

    public static Map<LineName, List<Stations>> getLocalHtmlStations(String path) {
        LocalHtmlParce localHtmlParce = new LocalHtmlParce();
        Document doc = localHtmlParce.parceLocalHtml(path);
        Map<LineName, List<Stations>> mapOfLinesAndStations = new LinkedHashMap<>();

        // Получаем все заголовки линий
        Elements linesHeaders = doc.select(".js-metro-line");

        // Обрабатываем каждую линию
        for (Element header : linesHeaders) {
            List<Stations> listOfStations = new LinkedList<>();
            // Идентификатор линии (номер)
            String lineNum = header.attr("data-line");
            // Имя линии
            String name = header.text();
            LineName lineName = new LineName(lineNum, name);
//            String lineName = lineNum + ". " + name;
            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");
            for (Element element : stationsElements) {
                //Название станции
                String stationName = element.text();
                // Поиск перехода
                String transition = element.select("span.t-icon-metroln").attr("title");
                // Определяем наличие перехода
                boolean hasConnection = transition != null && !transition.isEmpty();
                // Создаем объект станции В ЛЮБОМ СЛУЧАЕ
                Stations station = new Stations(stationName, transition, hasConnection);
                // Добавляем станцию в список текущей линии
                listOfStations.add(station);
            }
            // Добавляем готовый список станций в карту ПОСЛЕ завершения цикла
            mapOfLinesAndStations.put(lineName, listOfStations);
        }
        return mapOfLinesAndStations;
    }

}