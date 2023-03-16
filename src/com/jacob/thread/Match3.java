package com.jacob.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * {@code Callable} 与 {@code Future} 方式创建线程
 */
public class Match3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程池。里面天生有3个“空”线程。Executors是调度器，对线程池进行管理
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 实例化Callable对象
        Runner3 liuXiang = new Runner3();
        liuXiang.setName("刘翔");
        Runner3 jacob = new Runner3();
        jacob.setName("Jacob");
        Runner3 luFei = new Runner3();
        luFei.setName("路飞");

        // 将这个对象扔到线程池中，线程池自动分配一个线程来运行liuXiang这个对象的call方法
        // Future用于接受线程内部call方法的返回值
        Future<Integer> result1 = executorService.submit(liuXiang);
        Future<Integer> result2 = executorService.submit(jacob);
        Future<Integer> result3 = executorService.submit(luFei);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("刘翔累计跑了" + result1.get() + "米");
        System.out.println("Jacob累计跑了" + result2.get() + "米");
        System.out.println("路飞累计跑了" + result3.get() + "米");
    }
}

class Runner3 implements Callable<Integer> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    // 实现Callable接口可以允许我们的线程返回值或抛出异常
    @Override
    public Integer call() throws Exception {
        int speed = new Random().nextInt(100);
        // 总共奔跑的距离
        int distance = 0;
        for (int i = 1; i <= 100; ++i) {
            Thread.sleep(10);
            distance = i * speed;
            System.out.println(this.name + "已前进" + distance + "米(" + speed + "米/秒)");
        }
        return distance;
    }
}
