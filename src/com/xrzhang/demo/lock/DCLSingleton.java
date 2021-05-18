package com.xrzhang.demo.lock;


/**
 * @Program: demo
 * @Description: volatile禁止指令重排（通过JVM内存屏障来实现）
 * @Author: xrzhang
 * @Create: 2021-05-18 14:43
 */

public class DCLSingleton {

    private static volatile DCLSingleton dclSingleton;

    public static DCLSingleton getInstance() {
        if (dclSingleton == null) {
            synchronized (DCLSingleton.class) {
                if (dclSingleton == null) {
                    dclSingleton = new DCLSingleton();
                }
            }
        }
        return dclSingleton;
    }

    public void print() {
        System.out.println(Thread.currentThread().getName() + "DCL hello world!");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i ++) {
            new Thread(() -> {
               DCLSingleton.getInstance().print();
            }).start();
        }

    }

}