package com.jacob.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时锁 {@code CountDownLatch} 使用示例
 */
public class CountDownSample {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        // CDL总数和操作数保持一致
        CountDownLatch cdl = new CountDownLatch(10000);
        for (int i = 1; i <= 10000; ++i) {
            final int index = i;
            threadPool.execute(() -> {
                synchronized (CountDownSample.class) {
                    try {
                        count += index;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 计数器减一
                        cdl.countDown();
                    }
                }
            });
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            // 堵塞当前线程，知道cdl=0的时候再继续往下走
            cdl.await();
            // 为了避免程序一致挂起，我们可以设置一个timeout时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        threadPool.shutdown();
    }
}
