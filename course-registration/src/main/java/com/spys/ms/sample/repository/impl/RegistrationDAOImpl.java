package com.spys.ms.sample.repository.impl;

import org.springframework.stereotype.Repository;

import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.repository.RegistrationDAO;

/**
 *
 */
@Repository
public class RegistrationDAOImpl extends AbstractBaseDAO<Registration, Integer> implements RegistrationDAO
{

    @Override
    protected Class<Registration> getEntityClass()
    {
        return Registration.class;
    }
    
    public Registration update(Registration registration) {
    	Registration rg = get(registration.getRid());
    	rg.setGrade(registration.getGrade());
    	rg.setCourse(registration.getCourse());
    	rg.setStudent(registration.getStudent());
    	getSession().update(rg);
    	return rg;
    }
}
