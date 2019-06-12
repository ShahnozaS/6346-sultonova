package ru.cft.focusstart.shape;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

import java.text.DecimalFormat;

public class Rectangle extends GeometricFigure {
    private double length;
    private double width;
    private double diagonal;
    private double perimeter;
    private double area;

    public Rectangle(ShapeParams shapeParams) throws AppException {
        if (shapeParams.params.size() != 2) {
            throw new AppException("Wrong number of parameters");
        }
        length = Math.max(shapeParams.params.get(0), shapeParams.params.get(1));
        width = Math.min(shapeParams.params.get(0), shapeParams.params.get(1));

        if (length > 0 && width > 0) {
            diagonal = calcDiagonal();
            perimeter = calcPerimeter();
            area = calcArea();
        } else {
            throw new AppException("Parameters don't form a rectangle");
        }
    }

    private double calcDiagonal() {
        return Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }

    @Override
    public double calcPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public double calcArea() {
        return length * width;
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
