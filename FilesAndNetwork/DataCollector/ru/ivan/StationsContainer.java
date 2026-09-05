package ru.ivan;

import lombok.Data;

@Data
public class StationsContainer {
    // Поле для хранения объекта Stations
    private Stations stations;

    public StationsContainer(Stations stations) {
        this.stations = stations;
    }

    public StationsContainer() {}

}
