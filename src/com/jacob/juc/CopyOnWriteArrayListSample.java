package com.jacob.juc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListSample {
    public static void main(String[] args) {
        // 使用ArrayList会有并发问题
//        List<Integer> list = new ArrayList<>();
        // 写复制列表
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            list.add(i);
        }
        for (Integer i : list) {
            list.remove(i);
        }
        System.out.println(list);
    }
}
