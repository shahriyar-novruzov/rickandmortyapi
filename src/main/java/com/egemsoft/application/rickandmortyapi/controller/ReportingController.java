package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Report;
import com.egemsoft.application.rickandmortyapi.service.ReportingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for Reporting operations
 */
@Api("Rest Controller for Reporting operations")
@RestController
@RequestMapping(path = "${ms.root.url}/report")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    /**
     * @return statistics data about endpoints and history data about request and response
     */
    @ApiOperation("Get statistics about endpoint and history data")
    @GetMapping
    public Report getReport() {
        return reportingService.getReport();
    }
}
