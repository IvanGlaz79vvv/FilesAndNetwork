package ru.ivan;

import lombok.Data;

@Data
public class Stations {
    //    String num;
    String name;
    String depth;
    String transition;

    public Stations() {
    }

    public Stations(String name, String transition) {
        this.name = name;
        this.transition = transition;
    }

    public Stations(/*String num,*/ String name, String depth, String transition) {
//        this.num = num;
        this.name = name;
        this.depth = depth;
        this.transition = transition;
    }

    @Override
    public String toString() {
        if (transition.equals("")) {
            return "\t" + name;
        } else {
            return "\t" + name + " (" + transition + ")";
        }
    }
}