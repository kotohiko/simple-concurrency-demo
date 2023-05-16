package com.jacob.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类 {@code AtomicInteger} 演示
 */
public class AtomicIntegerSample {

    // 同时模拟的并发访问用户数量
    public static int userNumbers = 100;
    // 用户下载的真实总数
    public static int downTotal = 50000;
    // 计数器
    public static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        // 调度器，JDK 5 后提供的 concurrent 包对于并发的支持
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，用于模拟并发的人数
        final Semaphore semaphore = new Semaphore(userNumbers);
        for (int i = 0; i < downTotal; ++i) {
            executorService.execute(() -> {
                //通过多线程模拟N个用户并发访问并下载
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭调度服务
        executorService.shutdown();
        System.out.println("下载总数：" + count);
    }

    // 线程不安全？
    public static void add() {
        // count++
        count.getAndIncrement();
    }
}
