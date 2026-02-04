package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoscowMetroStations {
    public Map<String, String> getWebHtmlStations(String path) {
        Map<String, String> mapStations = new LinkedHashMap<>();
        {
            WebHtmlParce webHtmlParce = new WebHtmlParce();
            Document doc = webHtmlParce.parceWebHtml(path);
            //"span.t-metrostation-list-header"
            //div.t-metrostation-list-table
            Elements stations = doc.select("div.t-metrostation-list-table");
            Elements numbers = doc.select("span.num");
            Elements names = doc.select("span.name");
            for (int i = 0; i < numbers.size(); i++) {
                mapStations.put(numbers.get(i).text(), names.get(i).text());
            }
        }
        return mapStations;
    }

    public Map<String, List<Element>> getLocalHtmlStations(String path) {
        LocalHtmlParce localHtmlParce = new LocalHtmlParce();
        Document doc = localHtmlParce.parceLocalHtml(path);

        // Карта для хранения данных (ключ — название линии, значение — список станций)
        Map<String, List<Element>> mapOfLinesAndStations = new LinkedHashMap<>();

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
            Elements stations = doc.select("[data-line='" + lineDataAttr + "'] p.single-station span.name");

            mapOfLinesAndStations.put(lineName, stations);

            // Выводим все станции этой линии
//            for (Element station : stations) {
//                System.out.println("\tСтанция: " + station.text());
//            }
//
//            System.out.println(); // Разрыв между линиями
        }
        return mapOfLinesAndStations;
    }
}





            /*// Получаем название линии
            Elements namesOfLines = line.select(".t-metrostation-list-header");

            //Получаем номер линии
            String numbers = line.attr("data-line");

            // Определяем родительский контейнер станции (обычно рядом с заголовком)
            Element parentDiv = line.parent();
            while (!parentDiv.classNames().contains("js-depend")) {
                parentDiv = parentDiv.parent(); // поднимаемся вверх по дереву пока не найдем нужный родительский элемент
            }
            // Теперь в этом родительском блоке ищем станции
            Elements stations = parentDiv.select(".js-metro-stations p.single-station span.name");

            // Выводим информацию
            System.out.println("Линия №" + numbers + ": " + line.text());
            for (Element station : stations) {
                System.out.println("- " + station.text());
            }*/