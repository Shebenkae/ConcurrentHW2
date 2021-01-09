package ru.sbt.ex86;

import static java.lang.Thread.sleep;

public class Main {
    private static final int numberOfThreads = 10;

    public static void main(String[] args) throws InterruptedException {
//        Barrier barrier = new CounterBarrier(numberOfThreads);
//        for (int i = 0; i < numberOfThreads; ++i) {
//            new Thread(() -> {
//                foo();
//                barrier.await();
//                bar();
//            }).start();
//        }
        Barrier barrier2 = new ArrayBarrier(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            new Thread(() -> {
                foo();
                barrier2.await();
                bar();
            }).start();
        }
    }

    public static void foo() {
        System.out.println("foo");
    }

    public static void bar() {
        System.out.println("bar");
    }

}
