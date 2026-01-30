package ru.ivan;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.sound.sampled.Line;
import java.util.LinkedHashMap;
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
}
