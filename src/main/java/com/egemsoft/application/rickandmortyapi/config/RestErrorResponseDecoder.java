package com.egemsoft.application.rickandmortyapi.config;

import com.egemsoft.application.rickandmortyapi.exception.RestErrorException;
import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.RestErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * Decoder for Feign client when rest error occurs
 */
public class RestErrorResponseDecoder implements ErrorDecoder {

    private static final ESLogger logger = ESLogger.getLogger(RestErrorResponseDecoder.class);

    private final ObjectMapper objectMapper;

    public RestErrorResponseDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() == null) {
                return FeignException.errorStatus(methodKey, response);
            }

            final RestErrorResponse restError =
                    objectMapper.readValue(response.body().asInputStream(), RestErrorResponse.class);

            logger.error("Error while feign decoding, status: {} reading: {}, message: {}", response.status(),
                    methodKey,
                    restError.getMessage());
            return new RestErrorException(restError);
        } catch (IOException ex) {
            logger.error("decode.error", ex);
            return FeignException.errorStatus(methodKey, response);
        }
    }
}
