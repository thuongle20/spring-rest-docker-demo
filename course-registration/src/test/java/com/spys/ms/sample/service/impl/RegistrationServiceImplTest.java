package com.spys.ms.sample.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.repository.RegistrationDAO;
import com.spys.ms.sample.service.impl.RegistrationServiceImpl;
import com.spys.ms.sample.test.UnitTestBase;

/**
 *
 */
public class RegistrationServiceImplTest extends UnitTestBase
{

    private RegistrationDAO registrationDAO = Mockito.mock(RegistrationDAO.class, (Answer) (invocation) -> null);

    @Autowired
    private Registration registration;

    @Autowired
    private RegistrationServiceImpl courseService;

    @Before
    public void before()
    {
        this.courseService.setRegistrationDAO(this.registrationDAO);
    }

    @Test
    public void persist_update()
    {
        this.courseService.update(this.registration);
    }
}
