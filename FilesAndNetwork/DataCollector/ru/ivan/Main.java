package ru.ivan;

import org.jsoup.nodes.Document;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        WebParce webParce = new WebParce();
        Document htmlFile = webParce.parceSite("https://skillbox-java.github.io/");

        MoscowMetroLines moscowMetroLines = new MoscowMetroLines();

        Map<String, String> mapOfmoscowMetroLines = moscowMetroLines.getLines("https://skillbox-java.github.io/");
        mapOfmoscowMetroLines.forEach((key, value)-> System.out.println(key + ". " + value));
    }
}
