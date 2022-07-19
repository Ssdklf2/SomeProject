package org.example.lines;

import org.example.Line;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HelloImplTest {

    @Test
    void getString() {
        String helloString;
        try (ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext(
                "ApplicationContext.xml")) {
            Line hello = context.getBean("helloImpl", Line.class);
            helloString = hello.getString();
        }
        assertEquals("Hello", helloString);
    }
}