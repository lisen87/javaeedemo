package com.example.demo.mapper;

import com.example.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface StudentMapper {
    void addStudent(@Param("name") String name, @Param("age") int age, @Param("sex") int sex,@Param("birthday") Date birthday);

    List<Student> findAll();

}
