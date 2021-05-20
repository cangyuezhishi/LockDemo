package com.xrzhang.demo.lock;


import java.util.concurrent.Exchanger;

/**
 * @Program: LockDemo
 * @Description:  java.util.concurrent包中的Exchanger类可用于两个线程之间交换信息。
 * 可简单地将Exchanger对象理解为一个包含两个格子的容器，通过exchanger方法可以向两个格子中填充信息。
 * 当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，然后返回给线程，从而实现两个线程的信息交换。
 * @Author: xrzhang
 * @Create: 2021-05-19 08:50
 */

public class TestExchanger {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);

                System.out.println(Thread.currentThread().getName() + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();


        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);

                System.out.println(Thread.currentThread().getName() + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

    }

}