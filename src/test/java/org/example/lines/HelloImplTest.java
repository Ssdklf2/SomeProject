package org.example.lines;

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
            HelloImpl hello = context.getBean("helloImpl", HelloImpl.class);
            helloString = hello.getString();
        }
        assertEquals("Hello", helloString);
    }
}