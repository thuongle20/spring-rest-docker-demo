package com.spys.ms.sample.repository.impl;

import org.springframework.stereotype.Repository;

import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.repository.StudentDAO;

/**
 *
 */
@Repository
public class StudentDAOImpl extends AbstractBaseDAO<Student, Integer> implements StudentDAO
{

    @Override
    protected Class<Student> getEntityClass()
    {
        return Student.class;
    }
    
    public Student update(Student student) {
    	Student st = get(student.getSid());
    	st.setName(student.getName());
    	getSession().update(st);
    	return st;
    }
}
