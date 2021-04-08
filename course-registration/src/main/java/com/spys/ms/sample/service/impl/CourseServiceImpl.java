package com.spys.ms.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.repository.CourseDAO;
import com.spys.ms.sample.service.CourseService;

import lombok.Setter;

/**
 *
 */
@Setter
@Service
public class CourseServiceImpl extends AbstractBaseService<CourseDAO, Course, Integer> implements CourseService {

	@Autowired
	private CourseDAO courseDAO;

	@Override
	public CourseDAO getDAO() {
		return this.courseDAO;
	}

}
