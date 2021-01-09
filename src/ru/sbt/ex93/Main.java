package ru.sbt.ex93;

import java.util.ArrayDeque;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleReadWriteLock simpleReadWriteLock = new SimpleReadWriteLock();
        ArrayDeque<String> testQueue = new ArrayDeque<>();
        for (int i = 0; i < 3; i++){
            testQueue.addFirst("Initial string " + i);
        }
        for (int i = 1; i < 10; i++){
            if (i % 3 == 0) {
                new Thread(() -> writeToQueue(simpleReadWriteLock, testQueue)).start();
            } else {
                new Thread(() -> readFromQueue(simpleReadWriteLock, testQueue)).start();
            }
            sleep(1000);
        }
    }

    private static void readFromQueue(SimpleReadWriteLock simpleReadWriteLock, ArrayDeque<String> testQueue) {
        simpleReadWriteLock.readLock.lock();
        System.out.println("Read");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(testQueue.pollFirst());
        System.out.println("Finished");
        simpleReadWriteLock.readLock.unlock();
    }

    private static void writeToQueue(SimpleReadWriteLock simpleReadWriteLock, ArrayDeque<String> testQueue) {
        simpleReadWriteLock.writeLock.lock();
        try {
            sleep(100);
        } catch (InterruptedException ignored) {
        }
        System.out.println("write the value");
        simpleReadWriteLock.writeLock.unlock();

    }
}
