package com.helloswf.dao;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.helloswf.entities.Student;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Objects;

public class StudentDao extends AbstractDAO<Student> {
    @Inject
    public StudentDao(@Named("session")SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Session currentSession() {
        return super.currentSession();
    }

    public List<Student> getStudent(int rollNumber) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
        criteria.add(Restrictions.eq("rollNumber", rollNumber));
        super.currentSession().beginTransaction();
        List<Student> students = criteria.getExecutableCriteria(currentSession()).list();
        super.currentSession().close();
        return students;
    }

    public Student createOrUpdate(Student student) {
        if (Objects.isNull(student)) {
            return null;
        }
        super.currentSession().beginTransaction();
        Student savedStudent = super.persist(student);
        super.currentSession().flush();
        super.currentSession().close();
        return savedStudent;
    }
}
