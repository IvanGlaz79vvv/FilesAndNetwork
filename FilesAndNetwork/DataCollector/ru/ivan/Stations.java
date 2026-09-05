package ru.ivan;

import lombok.Data;
import ru.ivan.parse_JSON_CSV.ParceJSON;
import ru.ivan.parse_JSON_CSV.ParseCSV;

import java.util.*;

@Data
public class Stations {
    String name = null;
    String line = null;
    String date = null;
    Double depth = null;
    String transition = null;
    boolean hasConnection = false;

    public Stations() {
    }

    public Stations(String name, String transition, boolean hasConnection) {
        this.name = name;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, Double depth, String transition, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }

    public static Map<LineName, List<Stations>> getObjectStation(String path, String data) {
        Map<LineName, List<Stations>> mapOfAllStations = new LinkedHashMap<>();
        Map<LineName, List<Stations>> mapOfStations = MoscowMetroStations.getLocalHtmlStations(path);

        Map<String, Double> mapOfAllDepths = ParceJSON.parseJson(data);
        Map<String, String> mapOfAllDates = ParseCSV.parseCSV(data);

        for (LineName lines : mapOfStations.keySet()) {
            List<Stations> listOfAllStations = new LinkedList<>();
            String line = lines.getName().replaceAll("^[\\d\\w+]+\\.\\s+", "");
            Double depth = null;
            String date = null;
            Boolean hasConnection = false;

            for (Stations station : mapOfStations.get(lines)) {
                String name = station.getName().replaceAll("^[\\d\\w+]+\\.\\s+", "");
                String transition = station.getTransition();
                if (mapOfAllDepths.get(name) != null) {
                    depth = mapOfAllDepths.get(name);
                }
                date = mapOfAllDates.get(name);
                hasConnection = station.hasConnection;
                Stations stationForList = new Stations(name, line, date, depth, transition, hasConnection);
                listOfAllStations.add(stationForList);
                mapOfAllStations.put(lines, listOfAllStations);
            }
        }
        return mapOfAllStations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t{\n");

        boolean first = true;

        if (name != null) {
            if (!first) sb.append(",\n");
            sb.append("\t\t\t\"name\": \"").append(name).append("\"");
            first = false;
        }
        if (line != null) {
            if (!first) sb.append(",\n");
            sb.append("\t\t\t\"line\": \"").append(line).append("\"");
            first = false;
        }
        if (date != null) {
            if (!first) sb.append(",\n");
            sb.append("\t\t\t\"date\": \"").append(date).append("\"");
            first = false;
        }
        if (depth != null) {
            if (!first) sb.append(",\n");
            sb.append("\t\t\t\"depth\": ").append(depth);
            first = false;
        }
        if (transition != null) {
            if (!first) sb.append(",\n");
            sb.append("\t\t\t\"transition\": \"").append(transition).append("\"");
            first = false;
        }
        // hasConnection выводим всегда
        if (!first) sb.append(",\n");
        sb.append("\t\t\t\"hasConnection\": ").append(hasConnection);

        sb.append("\n\t\t}");
        return sb.toString();
    }
}
