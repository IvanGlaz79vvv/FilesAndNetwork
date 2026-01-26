package ru.ivan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebParce {

    public Document parceSite(String path) {
        Document doc = null;
        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        return doc;
    }
}
