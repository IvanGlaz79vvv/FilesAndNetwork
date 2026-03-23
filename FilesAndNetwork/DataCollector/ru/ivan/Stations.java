package ru.ivan;

import lombok.Data;

import java.util.Optional;
import java.util.OptionalInt;

@Data
public class Stations {
    String name;
    String line;
    String date;
    Integer depth;
    String transition;
    boolean hasConnection;

    public Stations() {
    }

    public Stations(String name, String transition, boolean hasConnection) {
        this.name = name;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, int depth) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
    }

    public Stations(String name, String line, String date, int depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, int depth, String transition, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.transition = transition;
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) sb.append(name + "; ");
        if (line != null) sb.append(line + "; ");
        if (date != null) sb.append(date + "; ");
        if (depth != null) sb.append(depth + "; ");
        if (transition != null) sb.append(transition/* + "; "*/);
        if (hasConnection != false) sb.append("; hasConnection = " + hasConnection);
        return sb.toString();
    }
}