package org.example.lines;

import org.example.Line;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class WorldImplTest {

    @Test
    void getString() {
        String worldString;
        try (ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext(
                "ApplicationContext.xml")) {
            Line world = context.getBean("worldImpl", Line.class);
            worldString = world.getString();
        }
        assertEquals("World", worldString);
    }
}