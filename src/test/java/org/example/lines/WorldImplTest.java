package org.example.lines;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class WorldImplTest {

    @Test
    void getString() {
        String worldString;
        try (ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext(
                "ApplicationContext.xml")) {
            WorldImpl world = context.getBean("worldImpl",WorldImpl.class);
            worldString = world.getString();
        }
        assertEquals("World", worldString);
    }
}