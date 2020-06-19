package com.egemsoft.application.rickandmortyapi.exception;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.logger.LoggerKeys;
import com.egemsoft.application.rickandmortyapi.model.RestErrorResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler for catch exceptions and handle them in correct ways before send response to client
 */
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final ESLogger log = ESLogger.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public RestErrorResponse handleNotFoundException(NotFoundException ex) {
        String errorId = MDC.get(LoggerKeys.REQUEST_ID);
        log.error("Error retrieving data through client.  {}", errorId, ex);
        return new RestErrorResponse(errorId,
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
