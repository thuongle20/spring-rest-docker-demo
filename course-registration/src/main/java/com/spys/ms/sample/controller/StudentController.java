package com.spys.ms.sample.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.service.StudentService;

import lombok.Setter;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    @Setter
    private StudentService studentService;

    /**
     * Persist a student bean into database.
     *
     * @param bean     student bean resembled from request body.
     * @param response
     *
     * @return ID of persisted bean.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Integer save(@RequestBody Student bean, HttpServletResponse response)
    {
        Student db = this.studentService.get(bean.getSid());
        if (null == db)
        {
            this.studentService.add(bean);
        }
        response.setStatus(db == null ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_CONFLICT);
        return bean.getSid();
    }

    /**
     * Update a student bean.
     *
     * @param id       primary key of target student.
     * @param bean     the newer student bean
     * @param response
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody Student bean, HttpServletResponse response)
    {
    	Student db = this.studentService.get(id);
        if (null != db)
        {
            bean.setSid(id);
            this.studentService.update(bean);
        } 
        response.setStatus(db == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * DELETE a student record from database.
     *
     * @param id       the target student id.
     * @param response
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Student bean = this.studentService.get(id);
        if (null != bean)
        {
            this.studentService.delete(bean);
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * GET a student record from database.<BR>
     *
     * @param id       primary key of target student.
     * @param response
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Student bean = this.studentService.get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
