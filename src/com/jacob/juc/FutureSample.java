package com.jacob.juc;

import java.util.concurrent.*;

public class FutureSample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 2; i <= 10000; i++) {
            Computer c = new Computer();
            c.setNum(i);
            // Future是对用于计算的线程进行监听，因为计算是在其他线程中执行的，所以这个返回结果的过程是异步的
            // 将c对象提交给线程池，如有空闲线程立即执行里面的call方法
            Future<Boolean> result = executorService.submit(c);
            try {
                // 用于获取返回值，如果线程内部的call没有执行完成，则进入等待状态，直到计算完成
                Boolean r = result.get();
                if (r) {
                    System.out.println(c.getNum());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}

class Computer implements Callable<Boolean> {

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public Boolean call() {
        boolean isPrime = true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}