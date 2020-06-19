package com.egemsoft.application.rickandmortyapi.helper;

import com.egemsoft.application.rickandmortyapi.logger.LoggerKeys;
import com.egemsoft.application.rickandmortyapi.model.History;
import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
import com.egemsoft.application.rickandmortyapi.repository.ReportingRepository;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Helper for create history from parameters and save in repository
 */
@Component
public class ReportingHelper {

    private final ReportingRepository reportingRepository;

    public ReportingHelper(ReportingRepository reportingRepository) {
        this.reportingRepository = reportingRepository;
    }

    /**
     * @param endpoint     endpoint type
     * @param errorMessage any error message during operation
     * @param responseData response data for the request
     */
    public void addHistory(Endpoint endpoint, String errorMessage, Object responseData) {
        History history = History
                .builder()
                .endpoint(endpoint)
                .requestUri(MDC.get(LoggerKeys.OPERATION))
                .requestId(MDC.get(LoggerKeys.REQUEST_ID))
                .userIp(MDC.get(LoggerKeys.USER_IP))
                .userAgent(MDC.get(LoggerKeys.USER_AGENT))
                .dateTime(LocalDateTime.now())
                .errorMessage(errorMessage)
                .responseData(responseData)
                .build();
        reportingRepository.addHistory(history);
    }
}
