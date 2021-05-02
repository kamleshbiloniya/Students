package com.helloswf.services;

import com.helloswf.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudent(int rollNumber);
    Student createOrUpdate (Student student);
}
