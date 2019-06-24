package ru.cft.focusstart.part2;

import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private int id;
    private static AtomicInteger count = new AtomicInteger(0);
    private BlockingQueue<Resource> store;
    private Logger log;

    Consumer(BlockingQueue<Resource> store, Logger log) {
        id = count.incrementAndGet();
        this.store = store;
        this.log = log;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
                Resource resource = store.take();

                MDC.put("threadNum", id + "");
                MDC.put("resourceId", resource.getId() + "");

                log.info(" Ресурс забран со склада");
                Thread.sleep(5000);
                log.info(" Ресурс потреблен");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.info("Завершение приложения");
        }
    }
}
