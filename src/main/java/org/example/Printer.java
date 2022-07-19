package org.example;

public class Printer {

    Line lineHello;
    Line lineWorld;

    public Printer(Line lineHello, Line lineWorld) {
        this.lineHello = lineHello;
        this.lineWorld = lineWorld;
    }

    public void print() {
        System.out.println((lineHello.getString() + ", " + lineWorld.getString() + "!"));
    }
}
