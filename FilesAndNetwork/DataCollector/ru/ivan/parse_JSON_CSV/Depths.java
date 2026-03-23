package ru.ivan.parse_JSON_CSV;

import lombok.Data;
import ru.ivan.MySearchFiles;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Depths {
    String station_name;
    double depth;
    public String errorMessage;

    public Depths(String station_name, double depth, String errorMessage) {
        this.station_name = station_name;
        this.depth = depth;
        this.errorMessage = errorMessage;
    }

    public Depths(String station_name, double depth) {
        this.station_name = station_name;
        this.depth = depth;
        this.errorMessage = null; // По умолчанию ошибки нет
    }

    public Depths(String station_name, String errorMessage) {
        this.station_name = station_name;
        this.errorMessage = errorMessage;
    }

    public Depths() {
    }

    @Override
    public String toString() {
        if (errorMessage != null) {
            // Если есть ошибка, выводим её вместо глубины
            return station_name + ", " + errorMessage;
        } else {
            return station_name + ", " + depth;
        }
    }

}
