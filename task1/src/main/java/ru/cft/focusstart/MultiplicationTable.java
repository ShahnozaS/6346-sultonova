package ru.cft.focusstart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        System.out.println("Введите размер таблицы от 1 до 32");
        int size = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            size = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введено некорректное значение");
        }
        if (size > 0 && size < 33) {
            TablePrinter.printTable(size);
        } else {
            System.out.println("Число не из диапазона [1, 32]");
        }
    }
}
