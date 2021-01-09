package ru.sbt.ex93;

public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}
