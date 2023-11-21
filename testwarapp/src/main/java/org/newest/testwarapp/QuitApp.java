package org.newest.testwarapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuitApp {

    private final ApplicationContext context;

    public QuitApp(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/quit")
    public void quitApplication() {
        if (context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) context).close();
        } else {
            System.exit(0);
        }
    }
}


