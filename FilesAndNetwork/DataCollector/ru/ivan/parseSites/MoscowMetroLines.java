package ru.ivan.parseSites;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.ivan.LineName;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoscowMetroLines {
    public /*Map<String, String>*/ List<LineName> getWebHtmlLines(String path) {
        List<LineName> listLines = new LinkedList<>();
        {
            WebHtmlParce webHtmlParce = new WebHtmlParce();
            Document doc = webHtmlParce.parceWebHtml(path);
            Elements lines = doc.select(".js-metro-line");
            if (!lines.isEmpty()) {
                for (Element l : lines) {
                    String name = l.text();
                    String id = l.attr("data-line");
                    LineName lineName = new LineName(id, name);
                    listLines.add(lineName);
                }
            }
        }
        return listLines;
    }

    public /*Map<String, String>*/ List<LineName> getlocalHtmlLines(String path) {
        List<LineName> listLines = new LinkedList<>();
        {
            LocalHtmlParce localHtmlParce = new LocalHtmlParce();
            Document doc = localHtmlParce.parceLocalHtml(path);
            Elements lines = doc.select(".js-metro-line");
            if (!lines.isEmpty()) {
                for (Element l : lines) {
                    String id = l.text();
                    String name = l.attr("data-line");
                    LineName lineName = new LineName(id, name);
                    listLines.add(lineName);
                }
            }
        }
        return listLines;
    }
}