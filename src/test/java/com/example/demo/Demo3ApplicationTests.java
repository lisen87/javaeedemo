package com.example.demo;

import com.example.demo.bean.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Demo3ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    StudentMapper studentMapper;

    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        Connection connection = dataSource.getConnection();
//        System.out.println("数据源："+connection);
//        connection.close();
        Calendar instance = Calendar.getInstance();
        instance.set(1988,Calendar.NOVEMBER,14);
//        studentMapper.addStudent("lisen",33,1,new Date(instance.getTimeInMillis()));
        List<Student> all = studentMapper.findAll();
        for (Student student : all) {
            System.out.println(student.toString());
        }
    }

}
