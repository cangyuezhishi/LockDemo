package com.xrzhang.demo.lock;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Program: LockDemo
 * @Description: 线程池测试小程序
 * @Author: xrzhang
 * @Create: 2021-05-19 17:12
 */

public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 说明：当提交一个任务时，线程池创建一个新线程执行任务，直到当前线程数等于corePoolSize；
         * 如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行.当阻塞队列塞满以后，
         * 如果继续有新的任务到来，则线程池创建新的线程来执行这个任务(新提交的任务，不是阻塞队列中的)，直到线程池中的线程数大于maximumPoolSize.
         *
         * 线程工厂：主要用来自定义线程名称
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),
                new OneMoreThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {

            Task task = new Task();

            System.out.println("-------------------");
            // 异步，没有返回值
            threadPoolExecutor.execute(task);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(task);
            System.out.println("coreSize" + threadPoolExecutor.getPoolSize());
            System.out.println("queue" + threadPoolExecutor.getQueue());
            System.out.println("-------------------");

            TimeUnit.SECONDS.sleep(1);

            // 同步，有返回值
//            threadPoolExecutor.submit(task);
        }


    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始执行！ ");

        try {
            TimeUnit.SECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 执行结束！ ");
    }
}


class OneMoreThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public OneMoreThreadFactory() {
        namePrefix = "OneMoreThread-" + poolNumber.getAndIncrement() + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread( r, namePrefix + threadNumber.getAndIncrement());
    }
}

