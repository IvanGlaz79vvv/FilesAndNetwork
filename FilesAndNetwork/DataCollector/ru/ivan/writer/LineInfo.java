package ru.ivan.writer;

import lombok.Data;

// 1. Класс для описания одной линии (для блока "lines")
@Data
class LineInfo {
    private int number;
    private String name;
    private String color;

    public LineInfo(int number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
    }
}
