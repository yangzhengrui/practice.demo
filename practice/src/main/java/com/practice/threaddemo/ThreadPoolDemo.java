package com.practice.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JDK提供了ExecutorService实现了线程池功能： 线程池内部维护一组线程，可以高效执行大量小任务；
 * Executors提供了静态方法创建不同类型的ExecutorService； FixedThreadPool：线程数固定的线程池；
 * CachedThreadPool：线程数根据任务动态调整的线程池； SingleThreadExecutor：仅单线程执行的线程池。
 * 必须调用shutdown()关闭ExecutorService； ScheduledThreadPool可以定期调度多个任务。
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // fixedThreadPoolDemo();
        scheduledThreadPoolDemo();
    }

    private static void scheduledThreadPoolDemo() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
        // 1秒后执行一次性任务:
        // ses.schedule(new TestTask("one-time"), 1, TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，每3秒执行:
        ses.scheduleAtFixedRate(new TestTask("fixed-rate"), 2, 3, TimeUnit.SECONDS);
        // 3秒后开始执行定时任务，以3秒为间隔执行:
        // ses.scheduleWithFixedDelay(new TestTask("fixed-delay"), 2, 3, TimeUnit.SECONDS);
    }

    private static void fixedThreadPoolDemo() {
        // 创建一个固定大小的线程池:
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            es.submit(new TestTask("" + i));
        }
        // 关闭线程池:
        es.shutdown();
    }

}