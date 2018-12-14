package com.mk.hibernate.demo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hbstudent")
public class Student {
    private int id;
    private String firstName;

    public Student() {

    }
}
