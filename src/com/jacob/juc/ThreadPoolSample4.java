package com.jacob.juc;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSample4 {
    public static void main(String[] args) {
        // 可调度线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 延迟三秒执行一次Run方法
        scheduledThreadPool.schedule(() -> System.out.println("延迟3秒执行"), 3, TimeUnit.SECONDS);
        // Timer，项目实际开发中 scheduledThreadPool 与 Timer 都不会用到，应为有成熟的调度框架 Quartz，或者 Spring 自带调度，
        // 程序的调度框架支持一种表达式叫做 Cron 表达式，有兴趣的童鞋可以了解一下。
        scheduledThreadPool.scheduleAtFixedRate(() ->
                System.out.println(new Date() + "延迟1秒执行，每三秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }
}
