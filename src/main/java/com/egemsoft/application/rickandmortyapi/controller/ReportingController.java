package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Report;
import com.egemsoft.application.rickandmortyapi.service.ReportingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${ms.root.url}/report")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping
    public Report getReport() {
        return reportingService.getReport();
    }
}
