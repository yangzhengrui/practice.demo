package com.practice.designdemo;

public class Phone {
    private Button[] digitButtons;
    private Button sendButton;

    public Phone() {
        digitButtons = new Button[10];
        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i] = new DigitButton();
            final int digit = i;
            digitButtons[i].addListener(new DigitButtonDailerAdepter(digit));
        }
        sendButton = new SendButton();
        sendButton.addListener(new SendButtonDailerAdepter());
    }

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.digitButtons[9].press();
        phone.digitButtons[1].press();
        phone.digitButtons[1].press();
        phone.sendButton.press();
    }
}