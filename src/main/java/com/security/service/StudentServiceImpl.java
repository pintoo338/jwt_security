package com.security.service;

import com.security.dao.StudentRepository;
import com.security.dao.UserRepository;
import com.security.entity.Student;
import com.security.entity.User;
import com.security.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements Studentservice {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Student> getallStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentbyId(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found"));
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(int id) {
        Student student = getStudentbyId(id); // Reuse existing method
        studentRepository.deleteById(id);
        return student;
    }

    @Override
    public Student updateStudent(int id, Student updatedData) {
        Student existingStudent = getStudentbyId(id);
        existingStudent.setFirstName(updatedData.getFirstName());
        existingStudent.setLastName(updatedData.getLastName());
        existingStudent.setEmail(updatedData.getEmail());
        existingStudent.setAddress(updatedData.getAddress());
        return studentRepository.save(existingStudent);
    }
 /*   public Student saveStudentWithUser(Student student, User user) {
        User savedUser = userRepository.save(user);
        student.setUser(savedUser);
        return studentRepository.save(student);
    }*/
}