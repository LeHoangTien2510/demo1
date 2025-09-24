package com.example.demo1.services;

import com.example.demo1.models.Student;

import java.util.List;

public interface StudentServiceInterface {
    List<Student> getStudents();
    List<Student> getStudents(String q,String sort,String dir,int limit);
    Student getStudentById(int id);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}
