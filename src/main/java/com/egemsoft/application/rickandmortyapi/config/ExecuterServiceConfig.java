package com.egemsoft.application.rickandmortyapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create executer thread poll for counting
 */
@Configuration
public class ExecuterServiceConfig {

    @Value("${executer.threadCount}")
    private int executerThreadCount;

    @Bean
    public ExecutorService getExecuterService() {
        return Executors.newFixedThreadPool(executerThreadCount);
    }
}
