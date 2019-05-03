package ru.cft.focusstart;

public class TablePrinter {
    public static void printTable(int size) {
        int maxElementLength = String.valueOf(size * size).length();
        for (int i = 1; i <= size; i++) {
            StringBuilder formattedLine = new StringBuilder();
            StringBuilder dividingLine = new StringBuilder();
            for (int j = 1; j <= size; j++) {
                int numberOfSpaces = maxElementLength - String.valueOf(i * j).length();
                addSpaces(formattedLine, numberOfSpaces);
                addMinus(dividingLine, maxElementLength);
                if (j != size) {
                    formattedLine.append(i * j).append("|");
                    dividingLine.append("+");
                } else {
                    formattedLine.append(i * j);
                }
            }
            System.out.println(formattedLine);
            System.out.println(dividingLine);
        }
    }

    private static void addSpaces(StringBuilder string, int numberOfSpaces) {
        for (int k = 0; k < numberOfSpaces; k++) {
            string.append(" ");
        }
    }

    private static void addMinus(StringBuilder string, int maxElementLength) {
        for (int k = 0; k < maxElementLength; k++) {
            string.append("-");
        }
    }
}

