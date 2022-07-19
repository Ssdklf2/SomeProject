package org.example.lines;

import org.example.Line;

public class HelloImpl implements Line {

    String string;

    public void setString(String string) {
        this.string = string;
    }

    public HelloImpl(String string) {
        this.string = string;
    }

    public HelloImpl() {
    }

    @Override
    public String getString() {
        return string;
    }
}
