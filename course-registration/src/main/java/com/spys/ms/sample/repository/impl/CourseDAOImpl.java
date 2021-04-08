package com.spys.ms.sample.repository.impl;

import org.springframework.stereotype.Repository;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.repository.CourseDAO;

/**
 *
 */
@Repository
public class CourseDAOImpl extends AbstractBaseDAO<Course, Integer> implements CourseDAO
{

    @Override
    protected Class<Course> getEntityClass()
    {
        return Course.class;
    }
    
    public Course update(Course course) {
    	Course cs = get(course.getCid());
    	cs.setName(course.getName());
    	getSession().update(cs);
    	return cs;
    }

}
