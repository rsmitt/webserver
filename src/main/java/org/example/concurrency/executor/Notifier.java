package org.example.concurrency.executor;

public class Notifier implements Runnable {

    private Message msg;
    private String THREAD_NAME;

    public Notifier(Message msg, String threadName) {
        this.msg = msg;
        THREAD_NAME = threadName;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " " + THREAD_NAME + " стартовал");
        try {
            Thread.sleep(3000);
            synchronized (msg) {
                msg.setMsg(name + " " + THREAD_NAME + " поток Notifier отработал");
                msg.notify();
                //msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
