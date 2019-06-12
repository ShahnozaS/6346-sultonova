package ru.cft.focusstart.shape;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

import java.text.DecimalFormat;

public class Circle extends GeometricFigure {
    private static double radius;
    private static double diameter;
    private static double perimeter;
    private static double area;

    public Circle(ShapeParams shapeParams) throws AppException {
        if (shapeParams.params.size() != 1) {
            throw new AppException("Wrong number of parameters");
        }
        if (shapeParams.params.get(0) >= 0) {
            radius = shapeParams.params.get(0);
            diameter = 2 * radius;
            perimeter = calcPerimeter();
            area = calcArea();
        } else {
            throw new AppException("Radius is negative");
        }
    }

    @Override
    public double calcPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calcArea() {
        return 2 * Math.PI * Math.pow(radius, 2);
    }

    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(FORMAT);
        return "Тип фигуры: Круг \r\n" +
                "Площадь: " + dFormat.format(area) + "\r\n" +
                "Периметр: " + dFormat.format(perimeter) + "\r\n" +
                "Радиус: " + dFormat.format(radius) + "\r\n" +
                "Диаметр: " + dFormat.format(diameter);
    }
}
