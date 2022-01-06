package com.chs.boot.sample.controller;

import com.chs.boot.sample.model.Student;
import com.chs.boot.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping(path = "/student")
    public List<Student> getStudentList(){
        return studentService.getStudents();
    }
}
