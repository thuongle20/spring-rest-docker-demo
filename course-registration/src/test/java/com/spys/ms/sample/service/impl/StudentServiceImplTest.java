package com.spys.ms.sample.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.repository.StudentDAO;
import com.spys.ms.sample.service.impl.StudentServiceImpl;
import com.spys.ms.sample.test.UnitTestBase;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentServiceImplTest extends UnitTestBase
{

    private StudentDAO studentDAO = Mockito.mock(StudentDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Student student;

    @Autowired
    private StudentServiceImpl studentService;

    @Before
    public void before()
    {
        this.studentService.setStudentDAO(this.studentDAO);
    }

    @Test
    public void update()
    {
        this.studentService.update(this.student);
    }
}
