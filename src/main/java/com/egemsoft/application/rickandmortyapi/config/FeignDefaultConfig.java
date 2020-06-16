package com.egemsoft.application.rickandmortyapi.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.egemsoft.application.rickandmortyapi.client")
public class FeignDefaultConfig {

}
