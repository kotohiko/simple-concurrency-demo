package com.jacob.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierSample {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(CyclicBarrierSample::go);
        }
        executorService.shutdown();
    }

    private static void go() {
        System.out.println(Thread.currentThread().getName() + ":准备就绪");
        try {
            // 设置屏障点，当累计 5 个线程都准备好后，才运行后面的代码
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + ":开始运行");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
