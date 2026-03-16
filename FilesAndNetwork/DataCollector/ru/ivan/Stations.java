package ru.ivan;

import lombok.Data;

@Data
public class Stations {
    //    String num;
    String name;
    String line;
    String date;
    String depth;
    boolean hasConnection;
    String transition;

    public Stations() {
    }

    public Stations(String name, String line, String date, String depth, boolean hasConnection, String transition) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
        this.transition = transition;
    }

    public Stations(String name, String transition) {
        this.name = name;
        this.transition = transition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) sb.append(name + "; ");
        if (line != null) sb.append(line + "; ");
        if (date != null) sb.append(date + "; ");
        if (date != null) sb.append(date + "; ");
        if (depth != null) sb.append(depth + "; ");
        if (hasConnection) sb.append("hasConnection: " + hasConnection + "; ");
        if (transition != null) sb.append(transition);
        return sb.toString();
    }

}