package ru.ivan.parse_JSON_CSV;

import lombok.Data;

@Data
public class Depths {
    String station_name;
    Double depth;

    public Depths(String station_name, Double depth) {
        this.station_name = station_name;
        this.depth = depth;
    }

    public Depths() {
    }

    @Override
    public String toString() {
            return station_name + ", " + depth;
    }
}