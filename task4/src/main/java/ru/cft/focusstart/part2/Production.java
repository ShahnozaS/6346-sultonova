package ru.cft.focusstart.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Production {
    private static final int STORE_SIZE = 5;
    private static final int DELAY = 30000;
    private static final Logger log = LoggerFactory.getLogger(Production.class);

    public static void main(String[] args) {
        BlockingQueue<Resource> store = new ArrayBlockingQueue<>(STORE_SIZE, true);

        Thread producer1 = new Thread(new Producer(store, log));
        Thread producer2 = new Thread(new Producer(store, log));
        Thread consumer = new Thread(new Consumer(store, log));

        producer1.setName("Производитель");
        producer2.setName("Производитель");
        consumer.setName("Потребитель");

        producer1.start();
        producer2.start();
        consumer.start();

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            log.info("Завершение приложения");
        }
        producer1.interrupt();
        producer2.interrupt();
        consumer.interrupt();
    }
}
