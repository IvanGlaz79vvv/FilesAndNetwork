package ru.ivan.writer;

import lombok.Data;
import ru.ivan.Stations;

import java.util.List;
@Data
public class StationContainer {
    private List<Stations> stations;

    // Конструктор
    public StationContainer(List<Stations> stations) {
        this.stations = stations;
    }

}
