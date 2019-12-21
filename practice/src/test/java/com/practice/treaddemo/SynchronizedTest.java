package com.practice.treaddemo;

import org.junit.jupiter.api.Test;

/**
 * 多线程同时读写共享变量时，会造成逻辑错误，因此需要通过synchronized同步；
 * 同步的本质就是给指定对象加锁，加锁后才能继续执行后续代码；
 * 注意加锁对象必须是同一个实例；
 * 对JVM定义的单个原子操作不需要同步。
 * 
 * 用synchronized修饰方法可以把整个方法变为同步代码块，synchronized方法加锁对象是this；
 * 通过合理的设计和数据封装可以让一个类变为“线程安全”；
 * 一个类没有特殊说明，默认不是thread-safe；
 * 多线程能否安全访问某个非线程安全的实例，需要具体问题具体分析。
 * 
 * Java的synchronized锁是可重入锁；
 * 死锁产生的条件是多线程各自持有不同的锁，并互相试图获取对方已持有的锁，导致无限等待；
 * 避免死锁的方法是多线程获取锁的顺序要一致。
 */
public class SynchronizedTest{

    @Test
    public void SynchronizedDemoTest() throws Exception{
        Thread  add = new AddThread();
        Thread  dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
    
}
class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}
class AddThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count -= 1;
            }
        }
    }
}