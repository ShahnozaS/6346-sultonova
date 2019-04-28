package ru.cft.focusstart.shape;

import ru.cft.focusstart.param.Pair;

import java.text.DecimalFormat;

public class Circle extends GeometricFigure {
    private final double PI = 3.1415;
    private double radius;
    private double diameter;
    private double perimeter;
    private double area;

    public Circle(Pair pair) {
        this.radius = pair.params.get(0);
        this.diameter = 2 * radius;
        this.perimeter = 0;
        this.area = 0;
    }

    @Override
    public void calcPerimeter() {
        perimeter = 2 * PI * radius;
    }

    @Override
    public void calcArea() {
        area = 2 * PI * sqr(radius);
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(FORMAT);
        return "Тип фигуры: Круг \r\n" +
                "Площадь: " + dFormat.format(area) + "\r\n" +
                "Периметр: " + dFormat.format(perimeter) + "\r\n" +
                "Радиус: " + dFormat.format(radius) + "\r\n" +
                "Диаметр: " + dFormat.format(diameter);
    }
}
