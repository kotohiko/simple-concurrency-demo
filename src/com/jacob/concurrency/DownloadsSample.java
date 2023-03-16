package com.jacob.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程安全/不安全的简单示例
 */
@SuppressWarnings("unused")
public class DownloadsSample {

    // 同时模拟的并发访问用户数量
    public static int usersNumber = 100;
    // 用户下载的真实总数
    public static int downTotal = 50000;
    // 计数器
    public static int count = 0;

    public static void main(String[] args) {
        // 调度器，JDK1.5后提供的concurrent包对于并发的支持
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，用于模拟并发的人数
        final Semaphore semaphore = new Semaphore(usersNumber);
        for (int i = 0; i < downTotal; ++i) {
            executorService.execute(() -> {
                // 通过多线程模拟N个用户并发访问并下载
                try {
                    semaphore.acquire();
                    // === 是否使用安全的方法在这里决定 ===
                    safeAdd();
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

    // 线程不安全
    public static void unsafeAdd() {
        ++count;
    }

    // 线程安全
    public synchronized static void safeAdd() {
        ++count;
    }
}
