package com.chs.boot.sample.mapper;

import com.chs.boot.sample.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findList();

    Student findOneByCode(String codes);

     void save(Student student);

     void update(Student student);

     void deleteById(Integer id);
}