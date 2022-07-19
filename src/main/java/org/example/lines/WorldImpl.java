package org.example.lines;

import org.example.Line;

public class WorldImpl implements Line {
    String string;

    public void setString(String string) {
        this.string = string;
    }

    public WorldImpl(String string) {
        this.string = string;
    }

    public WorldImpl() {
    }

    @Override
    public String getString() {
        return string;
    }
}
