package com.practice.designdemo;

public class DigitButtonDailerAdepter implements ButtonListener{

    private int token;

    public DigitButtonDailerAdepter(int token){
        this.token=token;
    }

    @Override
    public void buttonPressed() {
        new Dialer().enterDigit(token);
    }

}