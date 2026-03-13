package ru.ivan;

import lombok.Data;

@Data
public class Stations {
    String name;
    String line;
    String lineNum;
    String depth;
    String transition;
    boolean hasConnection;

    public Stations() {
    }

    public Stations(String name, String line, String lineNum, String depth) {
        this.name = name;
        this.line = line;
        this.lineNum = lineNum;
        this.depth = depth;
    }

    public Stations(String name, String line, String lineNum, String depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.lineNum = lineNum;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public Stations(String name, String line, String lineNum, String depth, String transition) {
        this.name = name;
        this.line = line;
        this.lineNum = lineNum;
        this.depth = depth;
        this.transition = transition;
    }

    @Override
    public String toString() {
        return name + "\n"
                + "\tлиния: " + line + "\n"
                + "\tдата: " + lineNum + "\n"
                + "\tглубина: " + depth;
    }
}
