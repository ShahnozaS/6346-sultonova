package ru.cft.focusstart.shape;

import ru.cft.focusstart.param.ParamsTriangle;

import java.text.DecimalFormat;

public class Triangle extends GeometricFigure {
    private double perimeter;
    private double area;
    private ParamsTriangle params;

    public Triangle(ParamsTriangle params) {
        this.params = params;
        perimeter = 0;
        area = 0;
    }

    @Override
    public void calcPerimeter() {
        this.perimeter = params.getSideA() + params.getSideB() + params.getSideC();
    }

    @Override
    public void calcArea() {
        double halfPerim = (params.getSideA() + params.getSideB() + params.getSideC()) / 2;
        area = Math.sqrt(halfPerim * (halfPerim - params.getSideA())
                * (halfPerim - params.getSideB()) * (halfPerim - params.getSideC()));
    }

    public void calcAngles() {
        double alfaA = Math.acos((sqr(params.getSideB()) + sqr(params.getSideC()) - sqr(params.getSideA()))
                / (2 * params.getSideB() * params.getSideC()));
        double bettaB = Math.acos((sqr(params.getSideA()) + sqr(params.getSideC()) - sqr(params.getSideB()))
                / (2 * params.getSideA() * params.getSideC()));
        double gammaC = Math.acos((sqr(params.getSideA()) + sqr(params.getSideB()) - sqr(params.getSideC()))
                / (2 * params.getSideA() * params.getSideB()));
        params.setAlfaSideA(Math.toDegrees(alfaA));
        params.setBettaSideB(Math.toDegrees(bettaB));
        params.setGammaSideC(Math.toDegrees(gammaC));
    }

    //проверка существования треугольника
    public boolean isTriangleExists(ParamsTriangle params) {
        return (params.getSideA() + params.getSideB()) > params.getSideC() &&
                (params.getSideB() + params.getSideC()) > params.getSideA() &&
                (params.getSideC() + params.getSideA()) > params.getSideB();
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(FORMAT);
        return "Тип фигуры: Треугольник \r\n" +
                "Площадь: " + dFormat.format(area) + "\r\n" +
                "Периметр: " + dFormat.format(perimeter) + "\r\n" +
                "Длина стороны а: " + dFormat.format(params.getSideA()) +
                ", угол: " + dFormat.format(params.getAlfaSideA()) + "\r\n" +
                "Длина стороны b: " + dFormat.format(params.getSideB()) +
                ", угол: " + dFormat.format(params.getBettaSideB()) + "\r\n" +
                "Длина стороны c: " + dFormat.format(params.getSideC()) +
                ", угол: " + params.getGammaSideC();
    }
}
