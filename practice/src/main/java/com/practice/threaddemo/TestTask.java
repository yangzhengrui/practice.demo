package com.practice.threaddemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTask implements Runnable{

    private final String name;

    public TestTask(String name){
        this.name=name;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("start task " + name+ " "+ sdf.format(new Date()));
        try {
            Thread.sleep(4000);
            //throw new InterruptedException("Test Scheduled");
        } catch (InterruptedException  e) {
            System.out.println(e.getMessage());
        }
        System.out.println("end task " + name+ " "+  sdf.format(new Date()));
    }
}