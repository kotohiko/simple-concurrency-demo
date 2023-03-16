package com.jacob.juc;

import java.util.concurrent.*;

public class FutureTaskSample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 2; i <= 10000; i++) {
            final int num = i;
            FutureTask<Boolean> task = new FutureTask<>(() -> {
                boolean isprime = true;
                for (int i1 = 2; i1 < num; i1++) {
                    if (num % i1 == 0) {
                        isprime = false;
                        break;
                    }
                }
                return isprime;
            });
            executorService.execute(task);
            try {
                if (task.get()) {
                    System.out.println(num);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
