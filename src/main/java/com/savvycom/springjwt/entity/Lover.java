package com.savvycom.springjwt.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Project: springjwt
 * Author: Nhokxayda at 27/08/21
 */

@Entity
@Table(name = "lover")
@Data
public class Lover {
    @Id @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "lover")
    private String lover;
    @Column(name = "mark")
    private int mark;
}
