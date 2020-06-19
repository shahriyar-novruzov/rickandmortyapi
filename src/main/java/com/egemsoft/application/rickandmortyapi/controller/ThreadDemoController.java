package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.ThreadDemo;
import com.egemsoft.application.rickandmortyapi.service.ThreadDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for ThreadDemo operations
 */
@Api("Rest Controller for ThreadDemo operations")
@RestController
@RequestMapping(path = "${ms.root.url}/thread-demo")
public class ThreadDemoController {

    private final ThreadDemoService threadDemoService;

    public ThreadDemoController(ThreadDemoService threadDemoService) {
        this.threadDemoService = threadDemoService;
    }

    /**
     * @return Characters name length counts counted by multiple threads
     */
    @ApiOperation("Get all Characters name length counts counted by multiple threads")
    @GetMapping
    public ThreadDemo countNameCharacters() {
        return threadDemoService.countNameCharacters();
    }
}
