package com.example.lab5student.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String ID;
    private String name;
    private int age;
    private String degree;
    private double GPA;
}
