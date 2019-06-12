package ru.cft.focusstart.shape;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

import java.text.DecimalFormat;

public class Triangle extends GeometricFigure {
    private double sideA;
    private double sideB;
    private double sideC;
    private double alfaSideA;
    private double bettaSideB;
    private double gammaSideC;
    private double perimeter;
    private double area;

    public Triangle(ShapeParams shapeParams) throws AppException {
        if (shapeParams.params.size() != 3) {
            throw new AppException("Wrong number of parameters");
        }
        sideA = shapeParams.params.get(0);
        sideB = shapeParams.params.get(1);
        sideC = shapeParams.params.get(2);

        if (isTriangleExists()) {
            perimeter = calcPerimeter();
            area = calcArea();
            calcAngles();
        } else {
            throw new AppException("Parameters don't form a triangle");
        }
    }

    @Override
    public double calcPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calcArea() {
        double halfPerim = (sideA + sideB + sideC) / 2;
        return Math.sqrt(halfPerim * (halfPerim - sideA) * (halfPerim - sideB) * (halfPerim - sideC));
    }

    private void calcAngles() {
        alfaSideA = Math.toDegrees(Math.acos((Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2))
                / (2 * sideB * sideC)));
        bettaSideB = Math.toDegrees(Math.acos((Math.pow(sideA, 2) + Math.pow(sideC, 2) - Math.pow(sideB, 2))
                / (2 * sideA * sideC)));
        gammaSideC = Math.toDegrees(Math.acos((Math.pow(sideA, 2) + Math.pow(sideB, 2) - Math.pow(sideC, 2))
                / (2 * sideA * sideB)));
    }

    //проверка существования треугольника
    private boolean isTriangleExists() {
        boolean isPositive = sideA > 0 && sideB > 0 && sideC > 0;
        boolean isTrExists = (sideA + sideB) > sideC && (sideB + sideC) > sideA && (sideC + sideA) > sideB;
        return isPositive && isTrExists;
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(FORMAT);
        return "Тип фигуры: Треугольник \r\n" +
                "Площадь: " + dFormat.format(area) + "\r\n" +
                "Периметр: " + dFormat.format(perimeter) + "\r\n" +
                "Длина стороны а: " + dFormat.format(sideA) +
                ", угол: " + dFormat.format(alfaSideA) + "\r\n" +
                "Длина стороны b: " + dFormat.format(sideB) +
                ", угол: " + dFormat.format(bettaSideB) + "\r\n" +
                "Длина стороны c: " + dFormat.format(sideC) +
                ", угол: " + dFormat.format(gammaSideC);
    }
}
