package ru.ivan.parse_JSON_CSV;

import lombok.Data;

@Data
public class MyDate {
    String name;
    String date;

    public MyDate(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return name + ", " + date ;
    }
}