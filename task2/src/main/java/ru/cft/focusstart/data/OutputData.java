package ru.cft.focusstart.data;

import ru.cft.focusstart.app.GeometricFigureApp;
import ru.cft.focusstart.param.Pair;

import java.io.FileWriter;
import java.io.IOException;

public class OutputData {
    //вывод в файл
    public static void printData(Pair pair, String fileOut) {
        switch (pair.type) {
            case "CIRCLE":
                writeToFile(fileOut, GeometricFigureApp.printCircle(pair));
                break;
            case "RECTANGLE":
                writeToFile(fileOut, GeometricFigureApp.printRectangle(pair));
                break;
            case "TRIANGLE":
                writeToFile(fileOut, GeometricFigureApp.printTriangle(pair));
                break;
            default:
                System.out.println("Wrong type");
                break;
        }
    }

    //вывод в консоль
    public static void printData(Pair pair) {
        switch (pair.type) {
            case "CIRCLE":
                System.out.println(GeometricFigureApp.printCircle(pair));
                break;
            case "RECTANGLE":
                System.out.println(GeometricFigureApp.printRectangle(pair));
                break;
            case "TRIANGLE":
                System.out.println(GeometricFigureApp.printTriangle(pair));
                break;
            default:
                System.out.println("Wrong type");
                break;
        }
    }

    private static void writeToFile(String fileOut, String outStr) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileOut, false);
            fileWriter.write(outStr);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
