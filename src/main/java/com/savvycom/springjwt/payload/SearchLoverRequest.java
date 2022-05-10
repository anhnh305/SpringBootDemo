package com.savvycom.springjwt.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Project: springjwt
 * Author: Nhokxayda at 01/09/21
 */

@Data
public class SearchLoverRequest {
    @NotNull
    private String field;
    @NotNull
    private String value;
    @NotNull
    private String sort;
}
