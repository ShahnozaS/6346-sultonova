package ru.cft.focusstart.data;

import ru.cft.focusstart.param.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputData {
    public static Pair readFromFile(String fileIn) {
        Pair pair = new Pair();
        try (Scanner fileScanner = new Scanner(new File(fileIn))) {
            if (fileScanner.hasNextLine()) {
                pair.type = fileScanner.next();
                readParams(fileScanner, pair);
            } else {
                System.out.printf("File %s is empty \n", fileIn);
            }
        } catch (FileNotFoundException fne) {
            System.out.println("File not found");
            return null;
        }
        return pair;
    }

    private static void readParams(Scanner scanner, Pair pair) {
        if (scanner.hasNextLine()) {
            pair.params = new ArrayList<>();
            while (scanner.hasNext()) {
                pair.params.add(scanner.nextDouble());
            }
        } else {
            System.out.println("Shape parameters not found in file");
        }
    }
}
