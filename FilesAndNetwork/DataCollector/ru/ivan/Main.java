package ru.ivan;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;


@Slf4j
public class Main {
    public static void main(String[] args) {
        String path = "html/Метро Москвы.html";
        String dataPath = "data";

        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(path, dataPath);

        for (LineName entry : mapOfAllStations.keySet()) {
            System.out.println();
            System.out.println(entry);
            System.out.println("{\n\t\"stations\": [");

            List<Stations> list = mapOfAllStations.get(entry);

            String joined = String.join(",\n", list.stream()
                    .map(Object::toString)
                    .toList());

            System.out.println(joined);
            System.out.println("\t]\n}");
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
