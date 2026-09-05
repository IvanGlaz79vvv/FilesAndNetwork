package ru.ivan;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class Main {
    public static void main(String[] args) {
        String path = "html/Метро Москвы.html";

        /*SearchFiles searchFiles = new SearchFiles();
        Map<String, List<String>> mapOfFiles = new HashMap<>();
        mapOfFiles = searchFiles.searchFiles("data");
        for (String entry : mapOfFiles.keySet()) {
            System.out.println(entry);
            for(String fileName : mapOfFiles.get(entry)){
                System.out.println(fileName);
            }
        }*/

        DataCollector dataCollector = new DataCollector();
        List<String> pathsOfJson = dataCollector.getListOfJson("data");
        pathsOfJson.forEach(System.out::println);

        List<String> pathsFromCsv = dataCollector.getListOfCsv("data");
        pathsFromCsv.forEach(System.out::println);
    }
}