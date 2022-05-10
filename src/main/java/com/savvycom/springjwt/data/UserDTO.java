package com.savvycom.springjwt.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Project: springjwt
 * Author: Nhokxayda at 30/08/21
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private int id;
        @NotEmpty(message = "Password không được để trống")
        private String password;
        @NotEmpty(message = "Username không được để trống")
        private String username;


}
