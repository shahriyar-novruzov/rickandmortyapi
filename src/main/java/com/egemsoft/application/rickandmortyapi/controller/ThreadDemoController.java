package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.ThreadDemo;
import com.egemsoft.application.rickandmortyapi.service.ThreadDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${ms.root.url}/thread-demo")
public class ThreadDemoController {

    private final ThreadDemoService threadDemoService;

    public ThreadDemoController(ThreadDemoService threadDemoService) {
        this.threadDemoService = threadDemoService;
    }

    @GetMapping
    public ThreadDemo countNameCharacters() {
        return threadDemoService.countNameCharacters();
    }
}
