package ru.ivan.parse_JSON_CSV;

import lombok.Data;
import ru.ivan.MySearchFiles;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Depths {
    String station_name;
    String depth;
//    int depth;

    public Depths(String station_name, String depth) {
        this.station_name = station_name;
        this.depth = depth;
    }

    public Depths() {
    }

    @Override
    public String toString() {
        return station_name + ", " + depth + ";";
    }

}
