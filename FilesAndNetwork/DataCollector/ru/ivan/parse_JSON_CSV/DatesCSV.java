package ru.ivan.parse_JSON_CSV;

import lombok.Data;

import java.time.Instant;

@Data
public class DatesCSV {
    String name;
    String date;

    public DatesCSV(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "(" +
                name + ", " +
                date + ")";
    }
}