package ru.ivan.parseSites;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebHtmlParce {

    public Document parceWebHtml(String path) {
        Document doc = null;
        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
