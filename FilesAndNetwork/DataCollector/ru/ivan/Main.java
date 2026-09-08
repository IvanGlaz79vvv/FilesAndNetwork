package ru.ivan;

import lombok.extern.slf4j.Slf4j;
import ru.ivan.DTO.LineName;
import ru.ivan.DTO.Stations;
import ru.ivan.Utils.DataCollector;
import ru.ivan.writer.WriteToJson;

import java.util.List;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String dataPath = "data";
        String htmlPath = "html/Метро Москвы.html";

        // собираем данные из всех JSON и CSV в папке
        Map<String, Integer> mapOfAllDepths = DataCollector.collectDepths(dataPath, "json");
        Map<String, String> mapOfAllDates = DataCollector.collectDates(dataPath, "csv");

        // склеиваем всё в итоговую структуру
        Map<LineName, List<Stations>> mapOfAllStations = Stations.getObjectStation(
                mapOfAllDepths,
                mapOfAllDates,
                htmlPath
        );

        String stationsWithDetails = "stationsWithDetails";
        String linesWithStations = "linesWithStations";
        WriteToJson.makeJsonFromMap(mapOfAllStations, stationsWithDetails);
        WriteToJson.makeMapJson(mapOfAllStations,linesWithStations);
//        log.info("Всего линий: {}", mapOfAllStations.size());
//        for(Map.Entry<LineName, List<Stations>> entry : mapOfAllStations.entrySet()) {
//            for(Stations stations : entry.getValue()) {
//                System.out.println(stations);
//            }
//        }
    }
}
