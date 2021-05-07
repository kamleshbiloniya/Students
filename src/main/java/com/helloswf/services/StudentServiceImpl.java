package com.helloswf.services;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.helloswf.entities.Student;
import com.helloswf.dao.StudentDao;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    @Inject
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @UnitOfWork
    public List<Student> getStudent(int rollNumber) {
        try{
            return studentDao.getStudent(rollNumber);
        } catch (Exception e) {
            return Lists.newArrayList(Student.getDefault());
        }
    }

    @Override
    @UnitOfWork
    public Student createOrUpdate(Student student) {
        return studentDao.createOrUpdate(student);
    }
}
