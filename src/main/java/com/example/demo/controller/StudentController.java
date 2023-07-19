package com.example.demo.controller;

import com.example.demo.bean.Student;
import com.example.demo.config.AopAnn;
import com.example.demo.service.LogService;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    final StudentService studentService;
    final LogService logService;

    public StudentController(StudentService studentService, LogService logService) {
        this.studentService = studentService;
        this.logService = logService;
    }
    @AopAnn
    @PostMapping(value = "/addStudent")
    public String addStudent(@RequestParam String name, @RequestParam int age, @RequestParam int sex, @RequestParam String birthday){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(birthday,dateTimeFormatter);
        Date date = Date.from( dateTime.atTime(LocalTime.MIDNIGHT).atZone( ZoneId.systemDefault()).toInstant());
        studentService.addStudent(name, age, sex, date);
        return "添加成功";
    }

    @AopAnn
    @GetMapping(value = "/getUserList")
    public List<Student> findAll(){
        return studentService.findAll();
    }
    @GetMapping(value = "/clearLog")
    public void clearLog(){
        logService.clear();
    }
}
