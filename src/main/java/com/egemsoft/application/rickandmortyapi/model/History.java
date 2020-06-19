package com.egemsoft.application.rickandmortyapi.model;

import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class History {

    private String requestId;
    private Endpoint endpoint;
    private String requestUri;
    private String userIp;
    private String userAgent;
    private LocalDateTime dateTime;
    private String errorMessage;
    private Object responseData;
}
