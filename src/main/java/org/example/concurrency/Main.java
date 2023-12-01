package org.example.concurrency;

import org.example.concurrency.thread.Message;
import org.example.concurrency.thread.Notifier;
import org.example.concurrency.thread.Waiter;

public class Main {
    public static void main(String[] args) {
        Message msg = new Message("обработать");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter,"waiter").start();

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("Стартовали все потоки");
    }
}
