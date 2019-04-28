package ru.cft.focusstart.shape;

public abstract class GeometricFigure {
    final String FORMAT = "#.##";

    double sqr(double number) {
        return number * number;
    }

    public abstract void calcPerimeter();

    public abstract void calcArea();
}
