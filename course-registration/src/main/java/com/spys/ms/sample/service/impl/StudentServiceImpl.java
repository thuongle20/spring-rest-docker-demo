package com.spys.ms.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.repository.StudentDAO;
import com.spys.ms.sample.service.StudentService;

import lombok.Setter;

/**
 *
 */
@Setter
@Service
public class StudentServiceImpl extends AbstractBaseService<StudentDAO, Student, Integer> implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public StudentDAO getDAO() {
		return this.studentDAO;
	}
}
