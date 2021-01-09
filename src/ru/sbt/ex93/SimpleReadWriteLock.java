package ru.sbt.ex93;

public class SimpleReadWriteLock implements ReadWriteLock {
    int readers;
    boolean writer;
    Lock readLock, writeLock;

    public SimpleReadWriteLock() {
        writer = false;
        readers = 0;
        readLock = new ReadLock();
        writeLock = new WriteLock();
    }

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }


    class ReadLock implements Lock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.this) {
                try {
                    while (writer) {
                        SimpleReadWriteLock.this.wait();
                    }
                    readers++;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.this) {
                readers--;
                if (readers == 0) {
                    SimpleReadWriteLock.this.notifyAll();
                }
            }
        }
    }

    class WriteLock implements Lock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.this) {
                try {
                    while (readers > 0 || writer) {
                        SimpleReadWriteLock.this.wait();
                    }
                    writer = true;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.this) {
                writer = false;
                SimpleReadWriteLock.this.notifyAll();
            }
        }
    }
}