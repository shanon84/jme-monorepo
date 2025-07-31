package com.example;

import com.example.mylib1.SimpleApplicationFactory;
import com.jme3.app.SimpleApplication;
import io.micronaut.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = ApplicationContext.run();
        log.info(context.getBeansOfType(SimpleApplicationFactory.class).toString());
        SimpleApplication app = context.getBean(SimpleApplication.class);
    }
}
