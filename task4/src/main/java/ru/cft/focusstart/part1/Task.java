package ru.cft.focusstart.part1;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {
    private int start;
    private int end;

    Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Double call() {
        double sum = 0;
        for (int i = start; i <= end; i++) {
            sum += Math.pow((i + 10), 2) + Math.log(i);
        }
        return sum;
    }
}
