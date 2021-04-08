package com.spys.ms.sample.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.repository.CourseDAO;
import com.spys.ms.sample.repository.RegistrationDAO;
import com.spys.ms.sample.repository.StudentDAO;
import com.spys.ms.sample.test.IntegrationTestBase;

/**
 *
 */
@Slf4j
public class RegistrationDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Registration registration;

    @Autowired
    private Course course;

    @Autowired
    private Student student;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RegistrationDAO registrationDAO;

    @Before
    public void setUp()
    {
        this.studentDAO.save(this.student);
        this.courseDAO.save(this.course);
        this.registrationDAO.save(this.registration);
    }

    @After
    public void tearDown()
    {
        this.registrationDAO.delete(this.registration);
        this.courseDAO.delete(this.course);
        this.studentDAO.delete(this.student);
    }

    @Test
    public void get()
    {
        Registration db = this.registrationDAO.get(this.registration.getRid());
        Assert.assertEquals(this.registration, db);
    }
}
