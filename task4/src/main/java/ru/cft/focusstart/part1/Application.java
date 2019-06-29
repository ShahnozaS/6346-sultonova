package ru.cft.focusstart.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Application {
    private static final int N = 1_000_000;
    private static final int POOL_SIZE = 10;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        List<Future<Double>> tasks = new ArrayList<>();

        int step = N / POOL_SIZE;

        int i;
        for (i = 1; i <= (POOL_SIZE - 1) * step; i += step) {
            tasks.add(threadPool.submit(new Task(i, i + step - 1)));
        }
        tasks.add(threadPool.submit(new Task(i, N)));

        List<Double> results = new ArrayList<>();

        for (Future<Double> task : tasks) {
            try {
                results.add(task.get());
            } catch (ExecutionException e) {
                log.info("An exception occurred during the calculation process");
            } catch (InterruptedException e) {
                log.info("Thread interrupted");
            }
        }
        double sum = 0;
        for (Double taskResult : results) {
            sum += taskResult;
        }
        log.info("Sum = " + sum);
        threadPool.shutdown();
    }
}
