package com.practice.designdemo;

import java.util.LinkedList;
import java.util.List;

public abstract class Button {
    
    private List<ButtonListener> buttonListeners;

    public Button() {
        this.buttonListeners = new LinkedList<ButtonListener>();
    }
    abstract void onPress();

    public void press() {
        onPress();
        for (ButtonListener listener : buttonListeners) { 
            listener.buttonPressed();
         }
    }

    public void addListener(ButtonListener buttonListener) {
        assert buttonListener != null; 
        buttonListeners.add(buttonListener);
    }

}