package com.savvycom.springjwt.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Project: springjwt
 * Author: Nhokxayda at 27/08/21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoverDTO {
    private int id;
    @NotEmpty(message = "Name không được để trống")
    private String name;
    @NotEmpty(message = "Lover không được để trống")
    private String lover;
    @NotEmpty(message = "Mark không được để trống")
    private int mark;
}

