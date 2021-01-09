package ru.sbt.ex86;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.onSpinWait;

public class TTAS implements Lock {
    private final AtomicBoolean isLocked; //реализация  test–and–test– and–set lock

    public TTAS() {
        isLocked = new AtomicBoolean(false);
    }

    @Override
    public void lock() {
        while (true) {
            while (isLocked.get()) {
                onSpinWait();
            }
            if (!isLocked.getAndSet(true)) {
                return;
            }
        }
    }

    @Override
    public void unlock() {
        isLocked.set(false);
    }
}
