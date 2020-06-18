package com.egemsoft.application.rickandmortyapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rest error response
 */
@ApiModel("Error response from Rest service")
@Data
@AllArgsConstructor
public class RestErrorResponse {

    @ApiModelProperty("Error unique ID")
    private String uuid;
    @ApiModelProperty("Error code - 404, 503 etc")
    private Integer code;
    @ApiModelProperty("Error message")
    private String message;
}
