package com.egemsoft.application.rickandmortyapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.egemsoft.application.rickandmortyapi.client")
public class FeignDefaultConfig {

    @Bean
    public Decoder notFoundAwareDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new NotFoundAwareDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
    }

    @Bean
    public ErrorDecoder restErrorResponseDecoder(ObjectMapper objectMapper) {
        return new RestErrorResponseDecoder(objectMapper);
    }
}
