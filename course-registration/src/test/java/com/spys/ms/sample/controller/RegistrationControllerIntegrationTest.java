package com.spys.ms.sample.controller;

import com.google.gson.Gson;
import com.spys.ms.sample.model.Course;
import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.model.Student;
import com.spys.ms.sample.service.CourseService;
import com.spys.ms.sample.service.StudentService;
import com.spys.ms.sample.test.ControllerClientSideTestBase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class RegistrationControllerIntegrationTest extends ControllerClientSideTestBase
{

    @Autowired
    private Gson GSON;

    @Autowired
    private Student student;

    @Autowired
    private Course course;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Registration registration;

    @Before
    public void setUp() throws Exception
    {
        this.courseService.getDAO().save(this.course);
        this.studentService.getDAO().save(this.student);
        this.save_201();
    }

    @After
    public void tearDown() throws Exception
    {
        this.delete_204();
        this.courseService.getDAO().delete(this.course);
        this.studentService.getDAO().delete(this.student);
    }

    private void save_201() throws Exception
    {
        MvcResult result = this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isCreated())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.registration.getRid());
    }

    @Test
    public void save_409() throws Exception
    {
        MvcResult result = this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isConflict())
            .andReturn();
        Integer resultID = Integer.parseInt(result.getResponse().getContentAsString());
        Assert.assertEquals(resultID, this.registration.getRid());
    }

    @Test
    public void update_404() throws Exception
    {
        this.mockMvc.perform(put("/registration/{rid}", 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.GSON.toJson(this.registration)))
            .andExpect(status().isNotFound());
    }

    private void delete_204() throws Exception
    {
        this.mockMvc.perform(delete("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void delete_404() throws Exception
    {
        this.mockMvc.perform(delete("/registration/{rid}", 0))
            .andExpect(status().isNotFound());
    }

    @Test
    public void get_200() throws Exception
    {
        this.mockMvc.perform(get("/registration/{rid}", this.registration.getRid()))
            .andExpect(status().isOk());
    }

    @Test
    public void get_404() throws Exception
    {
        this.mockMvc.perform(get("/registration/{rid}", 0))
            .andExpect(status().isNotFound());
    }
}
