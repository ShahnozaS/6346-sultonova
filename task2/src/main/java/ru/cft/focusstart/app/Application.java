package ru.cft.focusstart.app;

import ru.cft.focusstart.data.InputData;
import ru.cft.focusstart.data.OutputData;
import ru.cft.focusstart.exc.AppException;
import ru.cft.focusstart.param.ShapeParams;

public class Application {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Wrong number of arguments");
            return;
        }
        ShapeParams params;
        try {
            params = InputData.readFromFile(args[0]);
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            if (args.length == 2) {
                OutputData.printData(params, args[1]);
            } else {
                OutputData.printData(params);
            }
        } catch (AppException e) {
            System.out.println(e.getMessage());
        }
    }
}
