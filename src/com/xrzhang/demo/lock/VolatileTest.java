package com.xrzhang.demo.lock;


/**
 * @Program: demo
 * @Description: volatile可见性测试
 *
 * 说明： volatile关键字对于基本类型的修改可以在随后对多个线程的读保持一致，但是对于引用类型如数组，实体bean，仅仅保证引用的可见性，但并不保证引用内容的可见性。
 *
 * @Author: xrzhang
 * @Create: 2021-05-18 14:59
 */

public class VolatileTest {

    private static int x = 0;

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