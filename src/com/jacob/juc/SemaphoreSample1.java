package com.jacob.juc;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量 {@code Semaphore} 使用示例
 */
public class SemaphoreSample1 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 定义 5 个信号量，也就是说服务器只允许 5 个人在里面玩
        Semaphore semaphore = new Semaphore(5);
        // 循环次数为等待玩家数目
        for (int i = 1; i <= 20; ++i) {
            threadPool.execute(() -> {
                try {
                    // 获取一个信号量，“占用一个跑道”
                    semaphore.acquire();
                    play();
                    // 执行完成后释放这个信号量，“从跑道出去”
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    public static void play() {
        try {
            System.out.println(new Date() + " " + Thread.currentThread().getName() + ":获得紫禁之巅服务器进入资格");
            Thread.sleep(2000);
            System.out.println(new Date() + " " + Thread.currentThread().getName() + ":退出服务器");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
