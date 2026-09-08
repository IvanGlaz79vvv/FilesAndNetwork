package ru.ivan.parseSites;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class LocalHtmlParce {

    public Document parceLocalHtml(String path) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
