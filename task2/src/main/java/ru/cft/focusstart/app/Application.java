package ru.cft.focusstart.app;

import ru.cft.focusstart.data.InputData;
import ru.cft.focusstart.data.OutputData;
import ru.cft.focusstart.param.Pair;

public class Application {
    public static void main(String[] args) {
        if (args.length == 2) {
            String fileIn = args[0];
            String fileOut = args[1];
            Pair pair = InputData.readFromFile(fileIn);
            if (pair != null) {
                OutputData.printData(pair, fileOut);
            }
        } else if (args.length == 1) {
            String fileIn = args[0];
            Pair pair = InputData.readFromFile(fileIn);
            if (pair != null) {
                OutputData.printData(pair);
            }
        } else {
            System.out.println("No input file found");
        }
    }
}
