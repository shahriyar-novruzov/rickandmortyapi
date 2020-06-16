package com.egemsoft.application.rickandmortyapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base response class for Rest requests
 */
@ApiModel("Response from Rest service")
@Data
@NoArgsConstructor
public class RestResponse<T> {

    private ResponseInfo info;

    @ApiModelProperty("Response data")
    private T results;

    public RestResponse(T results) {
        this.results = results;
    }

    public static <T> RestResponse<T> of(T results) {
        return new RestResponse<>(results);
    }
}
