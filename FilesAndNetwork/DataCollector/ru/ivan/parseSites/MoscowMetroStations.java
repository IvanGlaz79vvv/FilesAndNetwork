package ru.ivan.parseSites;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.ivan.Stations;

import java.util.*;

public class MoscowMetroStations {
    public Map<String, List<Stations>> getWebHtmlStations(String path) {
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
//            System.out.println("\n" + lineNum + ". " + lineName);


            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");

            for (Element element : stationsElements) {
                //Название станции
                String stationName = element.text();
//                System.out.println("\telement.text(): " + element.text());

                //Переход
                String transition = element.select("span.t-icon-metroln").attr("title");
//                if(!transition.equals("")) System.out.println("transition: " + transition);

                Stations station = new Stations(stationName, transition);
                listOfStations.add(station);
//                System.out.println(station);

                mapOfLinesAndStations.put(lineName, listOfStations);

            }
            mapOfLinesAndStations.put(lineName, listOfStations);
        }
        return mapOfLinesAndStations;
    }


    public Map<String, List<Stations>> getLocalHtmlStations(String path) {
        LocalHtmlParce localHtmlParce = new LocalHtmlParce();
        Document doc = localHtmlParce.parceLocalHtml(path);
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
//            System.out.println("\n" + lineNum + ". " + lineName);


            // Находим ближайшие станции, связанные с этой линией
            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");

            for (Element element : stationsElements) {
                //Название станции
                String stationName = element.text();
//                System.out.println("\telement.text(): " + element.text());

                //Переход
                String transition = element.select("span.t-icon-metroln").attr("title");
//                if(!transition.equals("")) System.out.println("transition: " + transition);

                Stations station = new Stations(stationName, transition);
                listOfStations.add(station);
//                System.out.println(station);

                mapOfLinesAndStations.put(lineName, listOfStations);

            }
            mapOfLinesAndStations.put(lineName, listOfStations);
        }
        return mapOfLinesAndStations;
    }
}
