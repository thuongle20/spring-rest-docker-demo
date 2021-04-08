package com.spys.ms.sample.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.repository.StudentDAO;
import com.spys.ms.sample.test.IntegrationTestBase;

/**
 *
 */
@Slf4j
public class StudentDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Student student;

    @Autowired
    private StudentDAO studentDAO;

    @Before
    public void setUp()
    {
        this.studentDAO.save(this.student);
    }

    @After
    public void tearDown()
    {
        this.studentDAO.delete(this.student);
    }

    @Test
    public void get()
    {
        Student db = this.studentDAO.get(this.student.getSid());
        Assert.assertEquals(this.student, db);
    }
}
