package ru.sbt.ex86;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.onSpinWait;

public class CounterBarrier implements Barrier {
    private final int number;
    private final TTAS ttas;
    private final AtomicInteger counter; //больше барьеров памяти можно использовать просто инт
    // , однако больше гарантий happens-before

    public CounterBarrier(int numberOfThreads) {
        this.number = numberOfThreads;
        counter = new AtomicInteger(0);
        ttas = new TTAS();
    }

    @Override
    public void await() {
        ttas.lock();
        try {
            counter.incrementAndGet();
        } finally {
            ttas.unlock();
        }
        while (counter.get() < number) {
            onSpinWait();
        }
    }

}
