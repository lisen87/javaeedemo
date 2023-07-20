package com.example.demo.service;

import com.example.demo.bean.Student;

import java.util.Date;
import java.util.List;

public interface StudentService {
    void addStudent(String name, int age, int sex, Date birthday);
    List<Student> findAll();
    List<Student> getList(int page,int limit,String likeName);

}
