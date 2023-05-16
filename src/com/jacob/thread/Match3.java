package com.jacob.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * {@code Callable} 与 {@code Future} 方式创建线程
 */
public class Match3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程池。里面天生有 3 个 “空” 线程。Executors 是调度器，对线程池进行管理
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 实例化 Callable 对象
        Runner3 cao = new Runner3();
        cao.setName("曹操");

        Runner3 liu = new Runner3();
        liu.setName("刘备");

        Runner3 sun = new Runner3();
        sun.setName("孙权");

        // 将 cao 对象“扔”到线程池中，线程池自动分配一个线程来运行 cao 对象的 call 方法。其他两个对象也一样
        // Future 用于接受线程内部 call 方法的返回值
        Future<Integer> result1 = executorService.submit(cao);
        Future<Integer> result2 = executorService.submit(liu);
        Future<Integer> result3 = executorService.submit(sun);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("曹操累计跑了" + result1.get() + "米");
        System.out.println("刘备累计跑了" + result2.get() + "米");
        System.out.println("孙权累计跑了" + result3.get() + "米");
    }
}

class Runner3 implements Callable<Integer> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    // 实现 Callable 接口可以允许我们的线程返回值或抛出异常
    @Override
    public Integer call() throws Exception {
        int speed = new Random().nextInt(100);
        // 总共奔跑的距离
        int distance = 0;
        for (int i = 1; i <= 10; ++i) {
            Thread.sleep(10);
            distance = i * speed;
            System.out.println(i + this.name + "已前进" + distance + "米(" + speed + "米/秒)");
        }
        return distance;
    }
}
