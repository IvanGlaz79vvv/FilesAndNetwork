package ru.ivan;

import lombok.Data;

@Data
public class Stations {
    private String name;
    private String line;
    private String date;
    private Double depth;
    private String transition;
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

    public Stations(String name, String line, String date, double depth) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
    }

    public Stations(String name, String line, String date, double depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String date, double depth, String transition) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.transition = transition;
    }

    public Stations(String name, String line, String date, double depth, String transition, boolean hasConnection) {
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
        if (date != null){
            sb.append(date + "; ");
        }else{
            sb.append("date unknown; ");
        }
        if (depth != null) {
            sb.append(depth + "; ");
        } else {
            sb.append("no depth data; ");
        }
//        if (transition != null) sb.append(transition/* + "; "*/);
//        if (hasConnection) sb.append("; hasConnection = " + hasConnection);
//        sb.append("; hasConnection = " + hasConnection);
        sb.append(hasConnection);
        return sb.toString();
    }
}