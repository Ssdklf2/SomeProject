package org.example;

import org.example.lines.HelloImpl;
import org.example.lines.WorldImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    Line hello;
    Line world;
    PrintStream old;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        old = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        hello = new HelloImpl("Hello");
        world = new WorldImpl("World");
    }

    @AfterEach
    void tearDown() {
        System.setOut(old);
    }

    @Test
    void print() {
        var printer = new Printer(hello, world);
        printer.print();
        String result = String.valueOf(outputStream).replaceAll("\r\n", "");
        assertEquals("Hello, World!", result);
    }
}