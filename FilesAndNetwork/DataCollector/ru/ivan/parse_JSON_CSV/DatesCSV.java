package ru.ivan.parse_JSON_CSV;

import lombok.Data;

import java.util.Date;

@Data
public class DatesCSV {
    String name;
    Date date;

    public DatesCSV(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public DatesCSV() {
    }

    @Override
    public String toString() {
        return "Dates(" +
                name + ", " +
                date + ")";
    }
}