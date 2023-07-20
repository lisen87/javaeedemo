package com.example.demo.service.impl;

import com.example.demo.bean.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public void addStudent(String name, int age, int sex, Date birthday) {
        studentMapper.addStudent(name, age, sex, birthday);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<Student> getList(int page, int limit,String likeName) {
        return studentMapper.getList((page-1)* limit, limit,likeName);
    }
}
