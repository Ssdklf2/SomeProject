package org.example;

import org.example.lines.HelloImpl;
import org.example.lines.WorldImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ApplicationContext.xml")) {
            HelloImpl hello = context.getBean("helloImpl", HelloImpl.class);
            String helloString = hello.getString();
            System.out.println(helloString);

            WorldImpl world = context.getBean("worldImpl", WorldImpl.class);
            String worldString = world.getString();
            System.out.println(worldString);

            Printer printer = context.getBean("printer", Printer.class);
            printer.print();
        }
    }
}