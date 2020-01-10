package com.practice.designdemo;

public class SendButtonDailerAdepter implements ButtonListener{

    @Override
    public void buttonPressed() {
        new Dialer().dial();
    }

}