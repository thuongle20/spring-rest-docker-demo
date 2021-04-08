package com.spys.ms.sample.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.service.CourseService;

import lombok.Setter;

/**
 *
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	@Setter
	private CourseService courseService;

	/**
	 * Persist a course bean into database.
	 *
	 * @param bean     course bean resembled from request body.
	 * @param response
	 *
	 * @return ID of persisted bean.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Integer save(@RequestBody Course bean, HttpServletResponse response) {
		logger.debug("Adding new course id={}, name={}", bean.getCid(), bean.getName());
		Course db = this.courseService.get(bean.getCid());
		if (null == db) {
			bean = this.courseService.add(bean);
			logger.debug("Added new course id={}, name={}", bean.getCid(), bean.getName());
		} 
		response.setStatus(db == null ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_CONFLICT);
		return bean.getCid();
	}

	/**
	 * Update a course bean.
	 *
	 * @param id       primary key of target course.
	 * @param bean     the newer course bean
	 * @param response
	 *
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @RequestBody Course bean, HttpServletResponse response) {
		Course db = this.courseService.get(id);
		if (null != db) {
			bean.setCid(id);
			this.courseService.update(bean);
		}
		response.setStatus(db == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_NO_CONTENT);

	}

	/**
	 * DELETE a course record from database.
	 *
	 * @param id       the target course id.
	 * @param response
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id, HttpServletResponse response) {
		logger.debug("Deleting course {}", id);
		Course bean = this.courseService.get(id);
		if (null != bean) {
			this.courseService.delete(bean);
		}
		response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_NO_CONTENT);
	}

	/**
	 * GET a course record from database.<BR>
	 *
	 * @param id       primary key of target course.
	 * @param response
	 *
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable("id") Integer id, HttpServletResponse response) {
		Course bean = this.courseService.get(id);
		response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
		return bean;
	}
}
