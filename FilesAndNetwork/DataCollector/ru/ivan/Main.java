package ru.ivan;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class Main {
    public static void main(String[] args) {
        SearchFiles searchFiles = new SearchFiles();
        Map<String, List<String>> mapOfFiles = new HashMap<>();
        mapOfFiles = searchFiles.searchFiles("data");
        for (String entry : mapOfFiles.keySet()) {
            System.out.println(entry);
            for(String fileName : mapOfFiles.get(entry)){
                System.out.println(fileName);
            }
        }
    }
}

//        LocalHtmlParce localHtmlParce = new LocalHtmlParce();
//        Document docPesonalAccount = localHtmlParce.parceLocalHtml("html/Личный кабинет.html");
//        System.out.println(docPesonalAccount);

//        MetroUtils.printPathsOfFiles("data");/**Вывод путей имеющихся CSV и JSON с данными из папки datа в терминал*/
//        MetroUtils.printCsv("data");/**Вывод CSV папки datа в терминал*/
//        MetroUtils.printLinesOfLocalFile("html/Метро Москвы.html");/**Вывод линий с локального файла сайта*/

//        MetroUtils.printLinesOfWEB("https://skillbox-java.github.io/"); /**Вывод линий с сайта онлайн*/
//        MetroUtils.printLinesWithStationsSortedWEB("https://skillbox-java.github.io/");
//        MetroUtils.printLinesWithStationsSortedLocal("html/Метро Москвы.html");
//        MetroUtils.printGetObjectStationAsList("html/Метро Москвы.html", "data");/**Вывод с локального сайта в виде JSON*/
//        MetroUtils.printGetObjectStationAsTable("html/Метро Москвы.html", "data");/**Вывод с локального сайта в виде таблицы*/
//        MetroUtils.printJson("data");
//        WriteToJson.makeJSON("html/Метро Москвы.html", "data");
//        MetroUtils.getWebHtmlStations("https://skillbox-java.github.io/").forEach((K,V)-> System.out.println(K + "\t" + V +"\n"));
//        MetroUtils.recurringStations();
