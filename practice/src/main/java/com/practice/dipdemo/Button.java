package com.practice.dipdemo;

public class Button{

    private ButtonServer buttonServer;

    public Button(ButtonServer buttonServer){
        this.buttonServer=buttonServer;
    }

    public void poll() {
        buttonServer.turnOn();
    }
}