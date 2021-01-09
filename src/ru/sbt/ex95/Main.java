package ru.sbt.ex95;

public class Main {

    public static void main(String[] args) {
        Account savingAccount = new Account();
        for (int i = 1; i < 11; i++) {
            if (i % 3 == 0) {
                createThreadAndDeposit(savingAccount);
            } else if (i % 5 == 0) {
                createThreadAndPreferredWithdraw(savingAccount);
            } else {
                createThreadAndWithdraw(savingAccount);
            }
        }
    }

    public static void createThreadAndWithdraw(Account savingAccount) {
        new Thread(() -> {
            savingAccount.withdraw(100);
        }).start();
    }

    public static void createThreadAndDeposit(Account savingAccount) {
        new Thread(() -> {
            savingAccount.deposit(300);
        }).start();
    }

    public static void createThreadAndPreferredWithdraw(Account savingAccount) {
        new Thread(() -> {
            savingAccount.withdrawPreferred(400);
        }).start();
    }
}
