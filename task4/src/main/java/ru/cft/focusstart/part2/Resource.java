package ru.cft.focusstart.part2;

import java.util.concurrent.atomic.AtomicInteger;

public class Resource {
    private static AtomicInteger count = new AtomicInteger(0);
    private int id;

    Resource() {
        id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }
}
