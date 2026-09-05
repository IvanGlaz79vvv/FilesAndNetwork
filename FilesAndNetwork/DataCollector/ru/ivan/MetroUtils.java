//package ru.ivan;
//
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.jspecify.annotations.NonNull;
//import ru.ivan.parseSites.MoscowMetroLines;
//import ru.ivan.parseSites.WebHtmlParce;
//import ru.ivan.parse_JSON_CSV.MyParceJSON;
//import ru.ivan.parse_JSON_CSV.MyParseCSV;
//
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//public class MetroUtils {
//    public static void printGetObjectStationAsList(String path, String data) {
//        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, data);
//        for (LineName lineName : mapOfAllStations.keySet()) {
//            System.out.println("\n********************************************************\n" + lineName);
//            System.out.println("----------------------");
//            for (Stations stations : mapOfAllStations.get(lineName)) {
//                System.out.println(stations);
//            }
//        }
//    }
//
//    /**Возвращает Map<String, List<Stations>> */
//    public static Map<String, List<Stations>> getWebHtmlStations(String path){
//        WebHtmlParce webHtmlParce = new WebHtmlParce();
//        Document doc = webHtmlParce.parceWebHtml(path);
//        Map<String, List<Stations>> mapOfLinesAndStations = new LinkedHashMap<>();
//
//        // Получаем все заголовки линий
//        Elements linesHeaders = doc.select(".js-metro-line");
//
//        // Обрабатываем каждую линию
//        for (Element header : linesHeaders) {
//            List<Stations> listOfStations = new LinkedList<>();
//            // Идентификатор линии (номер)
//            String lineNum = header.attr("data-line");
//            // Имя линии
//            String name = header.text();
//            String lineName = lineNum + ". " + name;
//
//            // Находим ближайшие станции, связанные с этой линией
//            Elements stationsElements = doc.select("[data-line='" + lineNum + "'] p.single-station");
//            for (Element element : stationsElements) {
//                //Название станции
//                String stationName = element.text();
//                // Поиск перехода
//                String transition = element.select("span.t-icon-metroln").attr("title");
//                // Определяем наличие перехода
//                boolean hasConnection = transition != null && !transition.isEmpty();
//                // Создаем объект станции В ЛЮБОМ СЛУЧАЕ
//                Stations station = new Stations(stationName, transition, hasConnection);
//                // Добавляем станцию в список текущей линии
//                listOfStations.add(station);
//            }
//            // Добавляем готовый список станций в карту ПОСЛЕ завершения цикла
//            mapOfLinesAndStations.put(lineName, listOfStations);
//        }
//        return mapOfLinesAndStations;
//    }
//
//    public static void printGetObjectStationAsTable(String path, String data) {
//        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, data);
//        for (LineName line : mapOfAllStations.keySet()) {
//            System.out.println("=".repeat(120));
//            System.out.println("\n" + line.getName().toUpperCase());
//            System.out.println("—".repeat(168));
//            System.out.printf("%-20s| %-20s| %10s| %6s| %-67s| %-5b%n", "Станция", "Линия", "Дата", "Глубина", "Переход", "Наличие пересадки");
//            System.out.println("-".repeat(168));
//            for (Stations station : mapOfAllStations.get(line)) {
//                System.out.printf("%-20s| %-20s| %10s| %7.1f| %-67s| %-5b%n",
//                        station.getName(),
//                        station.getLine(),
//                        station.getDate(),
//                        station.getDepth(),
//                        station.getTransition(),
//                        station.hasConnection);
//            }
//        }
//    }
//
//    //Вывод путей имеющихся CSV и JSON с данными из папки datа в терминал
//    public static void printPathsOfFiles(String path) {
//        MyParseCSV.csvOutputToMap(path).entrySet().stream().forEach(entry -> {
////            System.out.println("size: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//        });
//        MyParceJSON.jsonOutput(path).entrySet().forEach(entry -> {
////            System.out.println("size: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//        });
//    }
//
//    //Вывод CSV папки datа в терминал
//    public static void printCsv(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод CSV");
//        MyParseCSV.csvOutputToMap(path).entrySet().stream().forEach(entry -> {
//            System.out.println("\nsize: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//            entry.getValue().forEach(value -> System.out.println("\t" + value));
//        });
//    }
//
//    public static void printJson(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод JSON");
//        MyParceJSON.jsonOutput(path).entrySet().forEach(entry -> {
//            System.out.println("\nsize: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//            entry.getValue().forEach(value -> System.out.println("\t" + value));
//        });
//    }
//
//    public static void printLinesOfLocalFile(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с локального файла сайта");
//        System.out.println("\nLOCAL Lines:");
//        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
//        moscowMetroLines.getlocalHtmlLines(path).forEach(line -> System.out.println(line));
//    }
//
//    //Вывод линий с сайта онлайн
//    public static void printLinesOfWEB(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод линий с сайта онлайн");
//        System.out.println("\nWEB Lines:");
//        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();
//        moscowMetroLines.getWebHtmlLines(path).forEach(line -> System.out.println(line));
//    }
//
//    public static void printLinesWithStationsSortedLocal(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с локального файла отсортированный по линиям");
//        MoscowMetroStations.getLocalHtmlStations(path).entrySet().forEach(entry -> {
//            System.out.println("\nsize: " + entry.getValue().size());
//            System.out.println(/*"\n" +*/ entry.getKey());
//            entry.getValue().forEach(value -> System.out.println("\t" + value));
//        });
//    }
//
//    public static void printLinesWithStationsSortedWEB(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с сайта отсортированный по линиям");
//        for (Map.Entry<String, List<Stations>> entry : /*MoscowMetroStations.*/getWebHtmlStations(path).entrySet()) {
//            System.out.println("\nsize: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//            entry.getValue().forEach(value -> System.out.println("\t" + value));
//        }
//    }
//
//    public static void getLinesWithStationsSortedWEB(String path) {
//        System.out.println("\n\n" + "*********************************\n" + "Вывод списка всех станций с сайта отсортированный по линиям");
//        for (Map.Entry<String, List<Stations>> entry : /*MoscowMetroStations.*/getWebHtmlStations(path).entrySet()) {
//            System.out.println("\nsize: " + entry.getValue().size());
//            System.out.println(entry.getKey());
//            entry.getValue().forEach(value -> System.out.println("\t" + value));
//        }
//    }
//
//    public static void recurringStations(@NonNull Map<String, List<Stations>> mapOfStations) {
//
//        // Шаг 1: Создаем единый поток и сохраняем его в коллекцию
//        List<Stations> allStations = mapOfStations.values().stream()
//                .flatMap(List::stream)
//                .toList(); // Сохраняем поток в коллекцию
//
//        // Шаг 2: Поиск дубликатов
//        List<Stations> hasConnectionList = new LinkedList<>();
//        List<Stations> recurringList = new LinkedList<>();
//        for (Stations station : allStations) {
//            if (station.hasConnection) {
//                hasConnectionList.add(station);
//                if (station.getTransition().contains(station.getName().replaceAll("^\\d+\\.\\s+", ""))) {
//                    recurringList.add(station);
//                }
//            }
//        }
//        System.out.println("\n**********  hasConnectionList  **********");
//        System.out.println("Size: " + hasConnectionList.size());
//        hasConnectionList.forEach(station -> System.out.printf("%-29s" + "%-25s%n", station.getName() + ": ", station.getTransition()));
//        System.out.println("\n**********  recurringList  **********");
//        System.out.println("Size: " + recurringList.size());
//        recurringList.forEach(station -> System.out.printf("%-29s" + "%-25s%n", station.getName() + ": ", station.getTransition()));
//    }
//}
//
