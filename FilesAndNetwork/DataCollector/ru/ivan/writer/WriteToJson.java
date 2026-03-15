package ru.ivan.writer;


import org.jsoup.select.Elements;
import ru.ivan.Stations;
import ru.ivan.parseSites.MoscowMetroStations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WriteToJson {

    public void makesAnEntryInJSON(String path){
        Stations stations = new Stations();
        MoscowMetroStations moscowMetroStations = new MoscowMetroStations();
        Map<String, List<Stations>> map = new LinkedHashMap<>();
        map = moscowMetroStations.getLocalHtmlStations(path);
        for (String s : map.keySet()) {
            System.out.println("\n" + s + ":");
//            map.get(s).forEach(l -> System.out.println("\t" + l.text()));
            map.get(s).forEach(System.out::println);
        }
    }
}
