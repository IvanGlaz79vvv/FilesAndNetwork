package ru.ivan.DTO;

import lombok.Data;

@Data
public class LineName {
    String id;
    String name;

    public LineName(String id) {
        this.id = id;
    }

    public LineName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
