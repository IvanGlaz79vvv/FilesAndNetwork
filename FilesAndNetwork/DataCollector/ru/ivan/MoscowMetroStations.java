package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.ivan.parseSites.LocalHtmlParce;
import ru.ivan.parseSites.MoscowMetroLines;
import ru.ivan.parseSites.WebHtmlParce;
import ru.ivan.parse_JSON_CSV.MyDate;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<MyDate> csvOutput(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
        MyParseCSV myParseCSV = new MyParseCSV();
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<MyDate> listOfCSV = new ArrayList<>();
        List<String> pathOfCSV = dataAddress.stream()
                .filter(s -> s.endsWith(".csv"))
                .collect(Collectors.toList());
        for (String path : pathOfCSV) {
            System.out.println();
            System.out.println(path);
            listOfCSV = myParseCSV.parseMyCSV(path);
//            listOfCSV.forEach(csv -> System.out.println("\t" + csv));
        }
        return listOfCSV;
    }

    public static void jsonOutput(String data) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
        MyParceJSON myParceJSON = new MyParceJSON();
        MySearchFiles mySearchFiles = new MySearchFiles();
        List<String> dataAddress = mySearchFiles.searchMyFiles(data);
        List<String> pathOfJson = dataAddress.stream()
                .filter(s -> s.endsWith(".json"))
                .collect(Collectors.toList());
        for (String path : pathOfJson) {
            System.out.println("\n" + path + ":");


        }
    }

    public static void linesOfLocalFile(String pathLocal) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
        System.out.println("\nLOCAL Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapLocal = moscowMetroLines.getlocalHtmlLines(pathLocal);
        mapLocal.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static void linesOfWEB(String path) {
        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта онлайн");
        System.out.println("\nWEB Lines:");
        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
        Map<String, String> mapWebLines = moscowMetroLines.getWebHtmlLines(path);
        mapWebLines.forEach((key, value) -> System.out.println(key + ". " + value));
    }


    public void getStationsWithAllPropertiesFromLocalFile(String path){
        Stations station = new Stations();
        for(String st:getLocalHtmlStations(path).keySet()){
            for(Stations sta: getLocalHtmlStations(path).get(st)){
                System.out.println(sta);
            }
        }

    }

}
