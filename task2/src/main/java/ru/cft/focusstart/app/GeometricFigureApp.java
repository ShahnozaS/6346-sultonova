package ru.cft.focusstart.app;

import ru.cft.focusstart.param.Pair;
import ru.cft.focusstart.param.ParamsTriangle;
import ru.cft.focusstart.shape.Circle;
import ru.cft.focusstart.shape.Rectangle;
import ru.cft.focusstart.shape.Triangle;

public class GeometricFigureApp {
    public static String printTriangle(Pair pair) {
        ParamsTriangle params;
        if (pair.params.size() == 3) {
            params = new ParamsTriangle(pair);
        } else {
            System.out.println("Wrong number of parameters");
            return "";
        }
        Triangle triangle = new Triangle(params);
        if (triangle.isTriangleExists(params)) {
            triangle.calcArea();
            triangle.calcPerimeter();
            triangle.calcAngles();
            return triangle.toString();
        } else {
            System.out.printf("Parameters %.2f %.2f %.2f don't form a triangle \n",
                    params.getSideA(), params.getSideB(), params.getSideC());
        }
        return "";
    }

    public static String printRectangle(Pair pair) {
        Rectangle rectangle;
        if (pair.params.size() == 2) {
            rectangle = new Rectangle(pair);
        } else {
            System.out.println("Wrong number of parameters");
            return "";
        }
        rectangle.calcDiagonal();
        rectangle.calcArea();
        rectangle.calcPerimeter();
        return rectangle.toString();
    }

    public static String printCircle(Pair pair) {
        Circle circle;
        if (pair.params.size() == 1) {
            circle = new Circle(pair);
        } else {
            System.out.println("Wrong number of parameters");
            return "";
        }
        circle.calcArea();
        circle.calcPerimeter();
        return circle.toString();
    }
}
