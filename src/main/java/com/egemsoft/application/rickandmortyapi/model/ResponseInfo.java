package com.egemsoft.application.rickandmortyapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ResponseInfo {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
