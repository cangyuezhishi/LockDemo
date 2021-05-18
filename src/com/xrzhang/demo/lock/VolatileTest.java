package com.xrzhang.demo.lock;


/**
 * @Program: demo
 * @Description: volatile可见性测试
 * @Author: xrzhang
 * @Create: 2021-05-18 14:59
 */

public class VolatileTest {

    private static Integer x = 0;

    //private static volatile Integer x = 0;

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {

            while (x == 0) {

            }
            System.out.println(Thread.currentThread().getName() + " x的值：" + x);
        });


        Thread t2 = new Thread(() -> {
            x = 1;
            System.out.println(Thread.currentThread().getName() + " x的值：" + x);
        });

        t1.start();
        //暂停1s
        Thread.sleep(1000);
        t2.start();
    }
}