package com.egemsoft.application.rickandmortyapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ThreadDemo {

    private List<String> result;
}
