package com.security.service;

import com.security.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Studentservice {


    Student getStudentbyId(int id);

    List<Student> getallStudents();

    Student addStudent(Student student);

    Student deleteStudent(int id);

    Student updateStudent(int id, Student student);

}
