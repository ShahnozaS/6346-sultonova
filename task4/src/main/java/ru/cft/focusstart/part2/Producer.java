package ru.cft.focusstart.part2;

import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static final int DELAY = 1000;
    private int id;
    private static AtomicInteger count = new AtomicInteger(0);
    private BlockingQueue<Resource> store;
    private Logger log;

    Producer(BlockingQueue<Resource> store, Logger log) {
        id = count.incrementAndGet();
        this.store = store;
        this.log = log;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Resource resource = new Resource();

                MDC.put("threadNum", id + "");
                MDC.put("resourceId", resource.getId() + "");

                log.info(" Ресурс произведен");
                Thread.sleep(DELAY);
                store.put(resource);
                log.info(" Ресурс помещен на склад");
            }
        } catch (InterruptedException e) {
            log.info("Завершение приложения");
        }
    }
}
