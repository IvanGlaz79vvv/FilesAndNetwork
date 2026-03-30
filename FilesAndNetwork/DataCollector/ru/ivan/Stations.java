package ru.ivan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ivan.parse_JSON_CSV.Depths;
import ru.ivan.parse_JSON_CSV.MyDate;
import ru.ivan.parse_JSON_CSV.MyParceJSON;
import ru.ivan.parse_JSON_CSV.MyParseCSV;

import java.util.*;

@Data
public class Stations {
    private String name;
    private String line;
    private String date;
    private Double depth;
    @JsonIgnore
    private String transition;
    boolean hasConnection;

    public Stations() {
    }

    public Stations(String name, String transition, boolean hasConnection) {
        this.name = name;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, double depth) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
    }

    public Stations(String name, String line, String date, double depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, double depth, String transition) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.transition = transition;
    }

    public Stations(String name, String line, String date, double depth, String transition, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }


    public static Map<LineName, List<Stations>> getObjectStation(String path, String data) {
        String name = null;
        String line = null;
        String date = null;
        double depth = 0;
        String transition = null;
        boolean connection = false;
        Map<LineName, List<Stations>> mapOfAllStations = new LinkedHashMap<>();


        Map<LineName, List<Stations>> mapOfStations = MoscowMetroStations.getLocalHtmlStations(path);
        Map<String, List<MyDate>> mapOfDates = MyParseCSV.csvOutput(data);
        Map<String, List<Depths>> mapOfDepths = MyParceJSON.jsonOutput(data);

        List<Depths> allDepths = new ArrayList<>();
        Map<String, Double> mapOfAllDepths = new HashMap<>();
        List<MyDate> allDates = new ArrayList<>();
        Map<String, String> mapOfAllDates = new HashMap<>();

        for (List<Depths> depthsList : mapOfDepths.values()) {
            allDepths.addAll(depthsList);
        }
        for (Depths depths : allDepths) {
            mapOfAllDepths.put(depths.getStation_name(), depths.getDepth());
        }

        for (List<MyDate> myDateList : mapOfDates.values()) {
            allDates.addAll(myDateList);
        }
        for (MyDate myDates : allDates) {
            mapOfAllDates.put(myDates.getName(), myDates.getDate());
        }

        for (LineName lines : mapOfStations.keySet()) {
            List<Stations> listOfAllStations = new LinkedList<>();
            line = lines.getName().replaceAll("^[\\d\\w+]+\\.\\s+", "");
            for (Stations station : mapOfStations.get(lines)) {
                name = station.getName().replaceAll("^[\\d\\w+]+\\.\\s+", "");
                transition = station.getTransition();
                connection = station.hasConnection;
                if (mapOfAllDepths.get(name) != null) {
                    depth = mapOfAllDepths.get(name);
                }
                date = mapOfAllDates.get(name);
                Stations stationForList = new Stations(name, line, date, depth, transition, connection);
                listOfAllStations.add(stationForList);
                mapOfAllStations.put(lines, listOfAllStations);
            }
        }
        return mapOfAllStations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) sb.append(name + "; ");
        if (line != null) sb.append(line + "; ");
        if (date != null){
            sb.append(date + "; ");
        }/*else{
            sb.append("date unknown; ");
        }*/
        if (depth != null) {
            sb.append(depth + "; ");
        }/* else {
            sb.append("no depth data; ");
        }*/
//        if (transition != null) sb.append(transition/* + "; "*/);
//        if (hasConnection) sb.append("; hasConnection = " + hasConnection);
//        sb.append("; hasConnection = " + hasConnection);
        sb.append(hasConnection);
        return sb.toString();
    }
}