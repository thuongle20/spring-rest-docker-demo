package com.spys.ms.sample.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.repository.CourseDAO;
import com.spys.ms.sample.test.IntegrationTestBase;

/**
 *
 */
@Slf4j
public class CourseDAOImplIntegrationTest extends IntegrationTestBase
{

    @Autowired
    private Course course;

    @Autowired
    private CourseDAO courseDAO;

    @Before
    public void setUp()
    {
        this.courseDAO.save(this.course);
    }

    @After
    public void tearDown()
    {
        this.courseDAO.delete(this.course);
    }

    @Test
    public void get()
    {
        Course db = this.courseDAO.get(this.course.getCid());
        Assert.assertEquals(this.course, db);
    }
}
