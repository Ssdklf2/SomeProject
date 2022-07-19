package org.example;

import org.example.lines.HelloImpl;
import org.example.lines.WorldImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ApplicationContext.xml")) {
            Line hello = context.getBean("helloImpl", Line.class);
            String helloString = hello.getString();
            System.out.println(helloString);

            Line world = context.getBean("worldImpl", Line.class);
            String worldString = world.getString();
            System.out.println(worldString);

            Printer printer = context.getBean("printer", Printer.class);
            printer.print();
        }
    }
}