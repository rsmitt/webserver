package org.example.concurrency.executor;

public class Waiter implements Runnable{

    private Message msg;
    private final String THREAD_NAME;

    public Waiter(Message m, String threadName){
        this.msg = m;
        THREAD_NAME = threadName;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{
                System.out.println(name + " " + THREAD_NAME + " ждем вызов метода notify: " + System.currentTimeMillis());
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name + " " + THREAD_NAME + " был вызов метода notify: " + System.currentTimeMillis());
            System.out.println(name + " " + THREAD_NAME + " : " + msg.getMsg());
        }
    }
}