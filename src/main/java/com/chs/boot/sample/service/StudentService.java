package com.chs.boot.sample.service;

import com.chs.boot.sample.mapper.StudentMapper;
import com.chs.boot.sample.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public List<Student> getStudents(){
        return studentMapper.findList();
    }
}
