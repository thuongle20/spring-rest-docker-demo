package com.spys.ms.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.repository.RegistrationDAO;
import com.spys.ms.sample.service.RegistrationService;

import lombok.Setter;

/**
 *
 */
@Setter
@Service
public class RegistrationServiceImpl extends AbstractBaseService<RegistrationDAO, Registration, Integer>
		implements RegistrationService {

	@Autowired
	private RegistrationDAO registrationDAO;

	@Override
	public RegistrationDAO getDAO() {
		return this.registrationDAO;
	}

}
