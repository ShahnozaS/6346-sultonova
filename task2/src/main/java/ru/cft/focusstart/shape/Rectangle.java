package ru.cft.focusstart.shape;

import ru.cft.focusstart.param.Pair;

import java.text.DecimalFormat;

public class Rectangle extends GeometricFigure {
    private double length;
    private double width;
    private double diagonal;
    private double perimeter;
    private double area;

    public Rectangle(Pair pair) {
        if (pair.params.get(0) < pair.params.get(1)) {
            length = pair.params.get(1);
            width = pair.params.get(0);
        } else {
            length = pair.params.get(0);
            width = pair.params.get(1);
        }
        this.perimeter = 0;
        this.area = 0;
    }

    public void calcDiagonal() {
        diagonal = Math.sqrt(sqr(length) + sqr(width));
    }

    @Override
    public void calcPerimeter() {
        perimeter = 2 * (length + width);
    }

    @Override
    public void calcArea() {
        area = length * width;
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(FORMAT);
        return "Тип фигуры: Прямоугольник \r\n" +
                "Площадь: " + dFormat.format(area) + "\r\n" +
                "Периметр: " + dFormat.format(perimeter) + "\r\n" +
                "Длина диагонали: " + dFormat.format(diagonal) + "\r\n" +
                "Длина: " + dFormat.format(length) + "\r\n" +
                "Ширина: " + dFormat.format(width);
    }
}
