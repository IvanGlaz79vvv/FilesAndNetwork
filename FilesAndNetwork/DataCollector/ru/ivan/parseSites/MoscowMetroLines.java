package ru.ivan.parseSites;

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