package com.egemsoft.application.rickandmortyapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base response class for Rest requests
 */
@ApiModel("Response from Rest service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse<T> {

    private ResponseInfo info;

    @ApiModelProperty("Response data")
    private T results;

    public RestResponse(T results, ResponseInfo responseInfo) {
        this.results = results;
        this.info = responseInfo;
    }

    public static <T> RestResponse<T> of(T results, ResponseInfo responseInfo) {
        return new RestResponse<>(results, responseInfo);
    }
}
