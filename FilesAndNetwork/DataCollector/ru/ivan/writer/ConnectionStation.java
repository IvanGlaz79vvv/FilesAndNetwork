package ru.ivan.writer;

import lombok.Data;

// 2. Класс для описания одной станции в пересадке (для блока "connections")
@Data
class ConnectionStation {
    private int line;
    private String station;

    public ConnectionStation(int line, String station) {
        this.line = line;
        this.station = station;
    }
}
