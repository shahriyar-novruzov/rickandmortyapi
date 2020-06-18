package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.History;
import com.egemsoft.application.rickandmortyapi.model.Report;
import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
import com.egemsoft.application.rickandmortyapi.repository.ReportingRepository;
import com.egemsoft.application.rickandmortyapi.service.ReportingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for operations of Reporting
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class ReportingServiceImpl implements ReportingService {

    private static final ESLogger logger = ESLogger.getLogger(ReportingServiceImpl.class);

    private final ReportingRepository reportingRepository;

    /**
     * @param reportingRepository Reporting repository for getting reported data
     */
    public ReportingServiceImpl(ReportingRepository reportingRepository) {
        this.reportingRepository = reportingRepository;
    }

    /**
     * @return endpoint statistics and history data report
     */
    @Override
    public Report getReport() {

        List<History> historyList = reportingRepository.getHistoryList();

        Map<Endpoint, Long> counting = historyList.stream().collect(
                Collectors.groupingBy(History::getEndpoint, Collectors.counting()));

        return Report
                .builder()
                .statistics(counting)
                .history(reportingRepository.getHistoryList())
                .build();
    }
}
