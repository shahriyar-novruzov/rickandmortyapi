package com.egemsoft.application.rickandmortyapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location extends Base {

    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
    private String created;
}
