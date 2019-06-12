package ru.cft.focusstart.data;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;
import ru.cft.focusstart.shape.Circle;
import ru.cft.focusstart.shape.GeometricFigure;
import ru.cft.focusstart.shape.Rectangle;
import ru.cft.focusstart.shape.Triangle;

public class ShapeCreator {
    public static GeometricFigure create(ShapeParams params) throws AppException {
        switch (params.type) {
            case "CIRCLE":
                return new Circle(params);
            case "RECTANGLE":
                return new Rectangle(params);
            case "TRIANGLE":
                return new Triangle(params);
            default:
                throw new AppException("Wrong type");
        }
    }
}
