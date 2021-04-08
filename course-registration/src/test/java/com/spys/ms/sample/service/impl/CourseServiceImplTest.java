package com.spys.ms.sample.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.repository.CourseDAO;
import com.spys.ms.sample.service.impl.CourseServiceImpl;
import com.spys.ms.sample.test.UnitTestBase;

/**
 *
 */
public class CourseServiceImplTest extends UnitTestBase
{

    private CourseDAO courseDAO = Mockito.mock(CourseDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Course course;

    @Autowired
    private CourseServiceImpl courseService;

    @Before
    public void before()
    {
        this.courseService.setCourseDAO(this.courseDAO);
    }

    @Test
    public void update()
    {
        this.courseService.update(this.course);
    }
}
