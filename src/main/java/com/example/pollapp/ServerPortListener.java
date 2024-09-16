package com.example.pollapp;

import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.stereotype.Component;

@Component
public class ServerPortListener implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServerApplicationContext webServerAppCtx = event.getApplicationContext();
        System.out.println("Application is running on port: " + webServerAppCtx.getWebServer().getPort());
    }

}
