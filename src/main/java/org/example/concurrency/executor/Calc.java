package org.example.concurrency.executor;

import java.util.concurrent.Callable;

public class Calc implements Callable<Integer> {

    private final int FIRST_DIGIT;
    private final int SECOND_DIGIT;

    public Calc(int firstDigit, int secondDigit) {
        FIRST_DIGIT = firstDigit;
        SECOND_DIGIT = secondDigit;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return FIRST_DIGIT + SECOND_DIGIT;
    }
}
