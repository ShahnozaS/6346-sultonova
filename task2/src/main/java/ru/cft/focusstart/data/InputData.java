package ru.cft.focusstart.data;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputData {
    public static ShapeParams readFromFile(String fileIn) throws AppException {
        try (Scanner fileScanner = new Scanner(new File(fileIn))) {
            ShapeParams shapeParams = new ShapeParams();
            if (fileScanner.hasNextLine()) {
                shapeParams.type = fileScanner.next();
            } else {
                throw new AppException("File is empty");
            }
            if (fileScanner.hasNextLine()) {
                shapeParams.params = readParams(fileScanner);
            } else {
                throw new AppException("Shape parameters not found in file");
            }
            return shapeParams;
        } catch (FileNotFoundException fne) {
            throw new AppException("File not found");
        }
    }

    private static List<Double> readParams(Scanner scanner) {
        List<Double> params = new ArrayList<>();
        while (scanner.hasNext()) {
            params.add(scanner.nextDouble());
        }
        return params;
    }
}
