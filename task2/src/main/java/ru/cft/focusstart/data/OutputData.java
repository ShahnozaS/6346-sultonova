package ru.cft.focusstart.data;

import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

import java.io.FileWriter;
import java.io.IOException;

public class OutputData {
    //вывод в файл
    public static void printData(ShapeParams params, String fileOut) throws AppException {
        writeToFile(fileOut, ShapeCreator.create(params).toString());
    }

    //вывод в консоль
    public static void printData(ShapeParams params) throws AppException {
        System.out.println((ShapeCreator.create(params)).toString());
    }

    private static void writeToFile(String fileOut, String outStr) {
        try (FileWriter fileWriter = new FileWriter(fileOut, false)) {
            fileWriter.write(outStr);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Errors occurred while writing data to a file");
        }
    }
}
