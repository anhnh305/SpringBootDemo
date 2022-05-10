package com.savvycom.springjwt.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Project: springjwt
 * Author: Nhokxayda at 27/08/21
 */

@Data
public class LoverRequest {
    @NotNull
    private String category;
}
