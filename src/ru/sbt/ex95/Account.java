package ru.sbt.ex95;

public class Account {
    int balance;
    int numPreferred;

    public Account() {
        balance = 0;
        numPreferred = 0;
    }

    public void deposit(int k) {
        synchronized (this) {
            balance += k;
            System.out.println("Added money " + k + " balance is " + balance);
            this.notifyAll();
        }
    }

    public void withdraw(int k) {
        synchronized (this) {
            while (balance < k || numPreferred > 0) {
                sleep();
            }
            balance -= k;
            System.out.println("subtracted money " + k + " balance is " + balance);
        }
    }

    public void withdrawPreferred(int k) {
        synchronized (this) {
            numPreferred++;
            while (balance < k) {
                sleep();
            }
            balance -= k;
            numPreferred--;
            System.out.println("subtracted " + k + " balance is " + balance);
            System.out.println();
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
