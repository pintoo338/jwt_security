package com.security.controller;

import com.security.entity.Student;
import com.security.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/admin")
public class StudentController {

    @Autowired
    private Studentservice studentservice;

    @GetMapping
    public List<Student> getStudents() {
        return this.studentservice.getallStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        return this.studentservice.getStudentbyId(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return this.studentservice.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        this.studentservice.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        return this.studentservice.updateStudent(id, student);
    }


}
