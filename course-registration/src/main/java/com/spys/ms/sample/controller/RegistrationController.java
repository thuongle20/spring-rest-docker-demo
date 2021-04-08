package com.spys.ms.sample.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spys.ms.sample.model.Registration;
import com.spys.ms.sample.service.RegistrationService;

import lombok.Setter;

@RestController
@RequestMapping("/registration")
public class RegistrationController
{
    @Autowired
    @Setter
    private RegistrationService registrationService;

    /**
     * Persist a registration bean into database.
     *
     * @param bean     registration bean resembled from request body.
     * @param response
     *
     * @return ID of persisted bean.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Integer save(@RequestBody Registration bean, HttpServletResponse response)
    {
        Registration db = this.registrationService.get(bean.getRid());
        if (null == db)
        {
            this.registrationService.add(bean);
        }
        response.setStatus(db == null ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_CONFLICT);
        return bean.getRid();
    }

    /**
     * Update a registration bean.
     *
     * @param id       primary key of target registration.
     * @param bean     the newer registration bean
     * @param response
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody Registration bean, HttpServletResponse response)
    {
        Registration db = this.registrationService.get(id);
        if (null != bean)
        {
            bean.setRid(id);
            this.registrationService.update(bean);
        }
        response.setStatus(db == null ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_CONFLICT);
    }

    /**
     * DELETE a registration record from database.
     *
     * @param id       the target registration id.
     * @param response
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Registration bean = this.registrationService.get(id);
        if (null != bean)
        {
            this.registrationService.delete(bean);
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * GET a registration record from database.<BR>
     *
     * @param id       primary key of target registration.
     * @param response
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        Registration bean = this.registrationService.get(id);
        response.setStatus(bean == null ? HttpServletResponse.SC_NOT_FOUND : HttpServletResponse.SC_OK);
        return bean;
    }
}
