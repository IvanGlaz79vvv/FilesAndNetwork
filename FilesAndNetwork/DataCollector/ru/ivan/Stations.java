package ru.ivan;

import lombok.Data;

@Data
public class Stations {
    String name;
    String line;
    String date;
    String depth;
    boolean hasConnection;


    public Stations() {
    }

    public Stations(String name, String line, String date, String depth) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
    }

    public Stations(String name, String line, String date, String depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return name + "\n"
                + "\tлиния: " + line + "\n"
                + "\tдата: " + date + "\n"
                + "\tглубина: " + depth;
    }
}
